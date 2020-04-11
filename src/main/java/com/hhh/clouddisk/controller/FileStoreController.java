package com.hhh.clouddisk.controller;

import com.hhh.clouddisk.entity.FileFolder;
import com.hhh.clouddisk.entity.FileStore;
import com.hhh.clouddisk.entity.MyFile;
import com.hhh.clouddisk.service.MyFileService;
import com.hhh.clouddisk.utils.FtpUtil;
import org.apache.commons.net.ftp.FTP;
import org.omg.CORBA.INTERNAL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FileStoreController extends  BaseController{

    /**
     * 上传文件
     */
    @PostMapping("/uploadFile")
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile files) {
        Map<String, Object> map = new HashMap<>();
//        上传到ftp服务器
//        更新mysql，insert file需要传递以下值：
//        my_file_name、file_store_id、my_file_path、parent_folder_id、size、type、postfix

        FileStore fileStore = fileStoreService.queryFileStoreByUserId(loginUser.getUserId());
        Integer folderId = Integer.valueOf(request.getHeader("id"));
        String name = files.getOriginalFilename().replaceAll(" ", "");
//        获取size
        Integer sizeInt = Math.toIntExact(files.getSize() / 1024);


//        判断同一文件目录下是否有相同文件（名字+后缀相同）
        List<MyFile> myFiles;
        if (folderId == 0){
            myFiles = myFileService.queryRootFileByFileStoreId(loginUser.getFileStoreId());
        }else {
            myFiles = myFileService.queryFileByParentFolderId(folderId);
        }
        for (int i=0;i<myFiles.size();i++){
            if ((myFiles.get(i).getMyFileName()+myFiles.get(i).getPostfix()).equals(name)){
                map.put("code", 501);
                return map;
            }
        }

//        判断文件大小是否在剩余容量范围内
        if(fileStore.getCurrentSize() + sizeInt > fileStore.getMaxSize()){
            map.put("code", 503);
            return map;
        }

//        获取files文件的myFileName和后缀postfix
        int indexDot = name.lastIndexOf(".");
        String myFileName = name.substring(0, indexDot);
        String postfix = name.substring(indexDot);

//        获取myFilePath
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());
        String myFilePath = loginUser.getUserId()+"/"+dateStr +"/"+folderId;
//        获取type
        int type = getType(postfix.toLowerCase());
//        上传到ftp服务器
        try {
            Boolean b = FtpUtil.uploadFile(myFilePath,name,files.getInputStream());
            if (b){
//              更新到数据库
                MyFile myfile = MyFile.builder()
                        .myFileName(myFileName)
                        .fileStoreId(loginUser.getFileStoreId())
                        .myFilePath(myFilePath)
                        .parentFolderId(folderId)
                        .size(sizeInt)
                        .postfix(postfix)
                        .type(type).build();
                myFileService.insertFile(myfile);
                fileStoreService.addSize(loginUser.getFileStoreId(),sizeInt);
                try{
                    Thread.sleep(5000);
                    map.put("code", 200);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                map.put("code", 504);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * 下载文件
     * fId:文件id
     */
    @GetMapping("/downloadFile")
    public void downloadFile(@RequestParam Integer fId){
//        String remotePath, String fileName, OutputStream outputStream


        MyFile myFile = myFileService.queryFileByMyFileId(fId);
        String myFileName = myFile.getMyFileName()+myFile.getPostfix();
        String myFilePath = myFile.getMyFilePath();
        Integer downloadTime = myFile.getDownloadTime();
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(response.getOutputStream());
            response.setCharacterEncoding("utf-8");
//            // 设置返回类型
            response.setContentType("multipart/form-data");
            // 文件名转码一下，不然会出现中文乱码
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(myFileName, "UTF-8"));

            boolean b = FtpUtil.downloadFile(myFilePath, myFileName, os);
            if (b){
                MyFile file = myFile.builder().myFileId(fId).downloadTime(downloadTime + 1).build();
                myFileService.updateFile(file);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }



    }

    /**
     * 删除文件
     */
    @GetMapping("/deleteFile")
    public String deleteFile(@RequestParam Integer fId,Integer folder){
//         String remotePath,String fileName

        MyFile myFile = myFileService.queryFileByMyFileId(fId);
        String myFileName = myFile.getMyFileName() + myFile.getPostfix();
        String myFilePath = myFile.getMyFilePath();
        if (FtpUtil.deleteFile(myFilePath,myFileName)){
            myFileService.deleteFileByMyFileId(fId);
            fileStoreService.subSize(myFile.getFileStoreId(),myFile.getSize());
        }
        return "redirct:/files?fId="+folder;
    }

    /**
     * 删除文件夹并清空文件
     */
    @GetMapping("/deleteFolder")
    public String deleteFolder(@RequestParam Integer fId){
//        String remotePath
        FileFolder fileFolder = fileFolderService.queryFileFolderByFileFolderId(fId);
        deleteFtpFolder(fId);
        return fileFolder.getParentFolderId()== 0?"redirect:/files":"redirect:/files?fId="+fileFolder.getParentFolderId();
    }

//    删除folderId相同的文件夹
    private void deleteFtpFolder(Integer fId){
//        获得该文件夹下的所有子文件夹
        List<FileFolder> fileFolders = fileFolderService.queryFileFolderByParentFolderId(fId);
//        获得该文件夹下的所有直属子文件
        List<MyFile> myFiles = myFileService.queryFileByParentFolderId(fId);
//        删除该文件夹中的所有直属子文件
        if (myFiles.size()!=0){
            for (int i = 0; i<myFiles.size();i++){
                MyFile myFile = myFiles.get(i);
                boolean b = FtpUtil.deleteFile(myFile.getMyFilePath(), myFile.getMyFileName() + myFile.getPostfix());
                if (b){
                    myFileService.deleteFileByMyFileId(myFile.getMyFileId());
                    fileStoreService.subSize(myFile.getFileStoreId(),myFile.getSize());
                }
            }
        }
//        删除该文件夹中的所有子文件夹中的文件
        if (fileFolders.size()!=0){
            for (int i = 0; i < fileFolders.size(); i++) {
                deleteFtpFolder(fileFolders.get(i).getFileFolderId());
            }
        }
//        把该文件夹下的所有文件、文件夹都删掉后，删除该文件夹
        fileFolderService.deleteFileFolderById(fId);
    }

    /**
     * 添加文件夹
     */
    @PostMapping("/addFolder")
    public String addFolder(FileFolder folder, Map<String, Object> map) {
        folder.setFileStoreId(loginUser.getFileStoreId());
        List<FileFolder> fileFolders ;

//        判断目录下是否同名文件夹
        if (folder.getParentFolderId() == null || folder.getParentFolderId() == 0){
//            代表根目录
            fileFolders = fileFolderService.queryRootFileFolderByFileStoreId(loginUser.getFileStoreId());
        }else {
//            代表当前的文件目录不是根目录
            fileFolders = fileFolderService.queryFileFolderByParentFolderId(folder.getParentFolderId());
        }
        for (int i = 0 ;i<fileFolders.size();i++){
            if (fileFolders.get(i).getFileFolderName().equals(folder.getFileFolderName())){
                return "redirect:/files?error=1&fId="+folder.getParentFolderId();
            }
        }

        fileFolderService.insertFileFolder(folder);
        return "redirect:/files?fId="+folder.getParentFolderId();
    }

    /**
     * 重命名文件夹
     */
    @PostMapping("/updateFolder")
    public String updateFolder(FileFolder folder,Map<String, Object> map) {
        FileFolder fileFolder = fileFolderService.queryFileFolderByFileFolderId(folder.getFileFolderId());
        fileFolder.setFileFolderName(folder.getFileFolderName());
        List<FileFolder> fileFolders ;

//        判断目录下是否同名文件夹
        if (fileFolder.getParentFolderId() == null || fileFolder.getParentFolderId() == 0){
//            代表根目录
            fileFolders = fileFolderService.queryRootFileFolderByFileStoreId(loginUser.getFileStoreId());
        }else {
//            代表当前的文件目录不是根目录
            fileFolders = fileFolderService.queryFileFolderByParentFolderId(fileFolder.getParentFolderId());
        }
        for (int i = 0 ;i<fileFolders.size();i++){
            if (fileFolders.get(i).getFileFolderName().equals(fileFolder.getFileFolderName())){
                return "redirect:/files?error=2&fId="+fileFolder.getParentFolderId();
            }
        }

        fileFolderService.updateFileFolderById(fileFolder);
        return "redirect:/files?fId="+fileFolder.getParentFolderId();
    }

    /**
     * 重命名文件
     * myFileName: bilibiliMail2
     * myFileId: 589
     */
    @PostMapping("/updateFileName")
    public String updateFileName(MyFile file, Map<String, Object> map) {
//        1、在FTP中更新文件名      reNameFile( String oldAllName,String newAllName)
//        2、更新数据库
        MyFile myFile = myFileService.queryFileByMyFileId(file.getMyFileId());
        String oldName = myFile.getMyFileName();
        String newName = file.getMyFileName();
        if (!oldName.equals(newName)){
            String oldAllName = myFile.getMyFilePath() + "/" +oldName+myFile.getPostfix();
            String newAllName = myFile.getMyFilePath() + "/" +newName+myFile.getPostfix();


            if (FtpUtil.reNameFile(oldAllName,newAllName)){
                myFileService.updateFile(MyFile.builder().myFileId(file.getMyFileId()).myFileName(newName).build());
            }
        }

        return "redirect:/files?fId="+myFile.getParentFolderId();
    }

    /**
     * 分享文件
     */
    @GetMapping("/file/share")
    public void shareFile(Integer f,String p,String t){
        return ;
    }

    public int getType(String type){
        if (".txt".equals(type)||".doc".equals(type)||".docx".equals(type)
                ||".wps".equals(type)||".word".equals(type)||".html".equals(type)||".pdf".equals(type)){
            return  1;
        }else if (".bmp".equals(type)||".gif".equals(type)||".jpg".equals(type)
                ||".pic".equals(type)||".png".equals(type)||".jepg".equals(type)||".webp".equals(type)
                ||".svg".equals(type)){
            return 2;
        } else if (".avi".equals(type)||".mov".equals(type)||".qt".equals(type)
                ||".asf".equals(type)||".rm".equals(type)||".navi".equals(type)||".wav".equals(type)
                ||".mp4".equals(type)){
            return 3;
        } else if (".mp3".equals(type)||".wma".equals(type)){
            return 4;
        } else {
            return 5;
        }

    }

}
