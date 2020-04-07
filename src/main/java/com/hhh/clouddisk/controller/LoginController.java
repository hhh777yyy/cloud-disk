package com.hhh.clouddisk.controller;

import com.alibaba.fastjson.JSON;

import com.hhh.clouddisk.entity.FileFolder;
import com.hhh.clouddisk.entity.FileStore;
import com.hhh.clouddisk.entity.MyFile;
import com.hhh.clouddisk.entity.User;
import com.hhh.clouddisk.service.FileFolderService;
import com.hhh.clouddisk.service.FileStoreService;
import com.hhh.clouddisk.service.MyFileService;
import com.hhh.clouddisk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    FileStoreService fileStoreService;

    @Autowired
    FileFolderService fileFolderService;

    @Autowired
    MyFileService myFileService;

    /*
     * 验证query是否可运行
     */
    @ResponseBody
    @GetMapping("/query/{userId}")
    public String queryUser(@PathVariable("userId") Integer userId){
        User user = userService.queryUserByUserId(userId);
        System.out.println("输出成功"+user.toString());
        String s = JSON.toJSONString(user);
        return s;
    }

    @GetMapping("/queryAll")
    public String queryAllUsers(){
        List<User> users = userService.queryAllUsers();
        System.out.println("输出成功"+users.toString());
        String s = JSON.toJSONString(users);
        return s;
    }

    /*
     * 验证update是否可运行
     */
    @GetMapping("update")
    public String updateUser(){
        User user =userService.queryUserByUserId(1);
        user.setUserName("100");

        if (userService.updateUser(user)){
            User user1 =userService.queryUserByUserId(1);
            System.out.println("输出成功"+user1.toString());
            String s = JSON.toJSONString(user1);
            return s;
        }else {
            return "false";
        }
    }

    @GetMapping("queryfile/{userId}")
    public String queryfile(@PathVariable("userId") Integer userId){
        FileStore fileStore = fileStoreService.queryFileStoreByUserId(userId);
        System.out.println("输出成功"+fileStore.toString());
        String s = JSON.toJSONString(fileStore);
        return s;
    }
    @GetMapping("queryfile2/{fileStoreId}")
    public String queryfile2(@PathVariable("fileStoreId") Integer fileStoreId){
        FileStore fileStore = fileStoreService.queryFileStoreByFileStoreId(fileStoreId);
        System.out.println("输出成功"+fileStore.toString());
        String s = JSON.toJSONString(fileStore);
        return s;
    }

    @GetMapping("subsize")
    public String addSize(@RequestParam("fileStoreId") Integer fileStoreId,@RequestParam("size") Double size){
        if(fileStoreService.subSize(fileStoreId, size)){
            FileStore fileStore = fileStoreService.queryFileStoreByUserId(fileStoreId);
            System.out.println("输出成功"+fileStore.toString());
            String s = JSON.toJSONString(fileStore);
            return s;
        }else {
            return "false";
        }
    }

    @GetMapping("insert/{userId}")
    public String insertFile(@PathVariable("userId") Integer userId){
        if(fileStoreService.insertFileStore(userId)){
            FileStore fileStore = fileStoreService.queryFileStoreByUserId(userId);
            System.out.println("输出成功"+fileStore.toString());
            String s = JSON.toJSONString(fileStore);
            return s;
        }else {
            return "false";
        }
    }

    @GetMapping("delete2/{fileStoreId}")
    public String deleteFile2(@PathVariable("fileStoreId") Integer fileStoreId){
        if(fileStoreService.delteFileStoreByFileStoreId(fileStoreId)){

            FileStore fileStore = fileStoreService.queryFileStoreByFileStoreId(fileStoreId);
            if (fileStore == null){
                return "shanchu chenggong";
            }else {
                System.out.println("输出成功"+fileStore.toString());
                String s = JSON.toJSONString(fileStore);
                return s;
            }

        }else {
            return "false";
        }
    }
    @GetMapping("queryf/{userId}")
    public String deleteFile(@PathVariable("userId") Integer userId){
        if(fileStoreService.delteFileStoreByUserId(userId)){

            FileStore fileStore = fileStoreService.queryFileStoreByUserId(userId);
            if (fileStore == null){
                return "shanchu chenggong";
            }else {
                System.out.println("输出成功"+fileStore.toString());
                String s = JSON.toJSONString(fileStore);
                return s;
            }

        }else {
            return "false";
        }
    }



    /*
     * fileFolder测试
     */
    @GetMapping("queryallfolder")
    public String queryAllFolder(){
        List<FileFolder> fileFolders = fileFolderService.queryAllFileFolders();
        System.out.println("输出成功"+fileFolders.toString());
        String s = JSON.toJSONString(fileFolders);
        return s;
    }

    @GetMapping("queryfolder/{fileFolderId}")
    public String queryFolderById(@PathVariable("fileFolderId") Integer fileFolderId){
        FileFolder fileFolder = fileFolderService.queryFileFolderByFileFolderId(fileFolderId);
        System.out.println("输出成功"+fileFolder.toString());
        String s = JSON.toJSONString(fileFolder);
        return s;
    }

    @GetMapping("queryfolder2/{fileFolderName}")
    public String queryFolderByName(@PathVariable("fileFolderName") String fileFolderName){
        FileFolder fileFolder = fileFolderService.queryFileFolderByFileFolderName(fileFolderName);
        System.out.println("输出成功"+fileFolder.toString());
        String s = JSON.toJSONString(fileFolder);
        return s;
    }

    @GetMapping("queryfolder3/{parentFolderId}")
    public String queryFolderByParentId(@PathVariable("parentFolderId") Integer  parentFolderId){
        List<FileFolder> fileFolders = fileFolderService.queryFileFolderByParentFolderId(parentFolderId);
        System.out.println("输出成功"+fileFolders.toString());
        String s = JSON.toJSONString(fileFolders);
        return s;
    }

    @GetMapping("queryfolder4/{fileStoreId}")
    public String queryFolderByStoreId(@PathVariable("fileStoreId") Integer  fileStoreId){
        List<FileFolder> fileFolders = fileFolderService.queryFileFolderByFileStoreId(fileStoreId);
        System.out.println("输出成功"+fileFolders.toString());
        String s = JSON.toJSONString(fileFolders);
        return s;
    }

    @GetMapping("insertfolder")
    public String insertFolder(@RequestParam("fName") String fileFolderName,@RequestParam("fId") Integer fileStoreId){
        FileFolder fileFolder = new FileFolder();
        fileFolder.setFileFolderName(fileFolderName);
        fileFolder.setFileStoreId(fileStoreId);
        if (fileFolderService.insertFileFolder(fileFolder)){
            FileFolder fileFolder1 = fileFolderService.queryFileFolderByFileFolderName(fileFolder.getFileFolderName());
            System.out.println("输出成功"+fileFolder1.toString());
            String s = JSON.toJSONString(fileFolder1);
            return s;
        }else {
            return "false";
        }
    }

    @GetMapping("deletefolder/{fileFolderId}")
    public String deleteFolder(@PathVariable("fileFolderId") Integer fileFolderId){
        if (fileFolderService.deleteFileFolderById(fileFolderId)){
            return "shanchu chenggong";
        }else {
            return "false";
        }
    }

    @GetMapping("updateFolder")
    public String updateFolder(@RequestParam("fName") String fileFolderName){
        FileFolder fileFolder = new FileFolder();
        fileFolder.setFileFolderId(14);
        fileFolder.setFileFolderName(fileFolderName);
        if (fileFolderService.updateFileFolderById(fileFolder)) {
            FileFolder fileFolder1 = fileFolderService.queryFileFolderByFileFolderId(fileFolder.getFileFolderId());
            System.out.println("更改成功"+fileFolder1.toString());
            String s = JSON.toJSONString(fileFolder1);
            return s;
        }else {
            return "false";
        }
    }



    @GetMapping("queryfilefile/{myFileId}")
    public String queryFile(@PathVariable("myFileId") Integer myFileId){
        MyFile file = MyFile.builder().myFileId(myFileId).build();
        System.out.println(file.toString());
        List<MyFile> myFiles = myFileService.queryFileAnd(file);
        System.out.println("更改成功"+myFiles.toString());
        String s = JSON.toJSONString(myFiles);
        return s;
    }

    @GetMapping("queryfilefile2/{myFileId}")
    public String queryFile2(@PathVariable("myFileId") Integer myFileId){
        MyFile file = MyFile.builder().myFileId(myFileId).build();
        System.out.println(file.toString());
        List<MyFile> myFiles = myFileService.queryFileOr(file);
        System.out.println("更改成功"+myFiles.toString());
        String s = JSON.toJSONString(myFiles);
        return s;
    }

    @GetMapping("insertfilefile")
    public String insertFile(){
        MyFile file = MyFile.builder().myFileName("111").fileStoreId(1).myFilePath("//ss").size(1024).type(1).postfix(".exe").build();
        if (myFileService.insertFile(file)){
            List<MyFile> myFiles = myFileService.queryFileAnd(file);
            System.out.println("zengjia成功"+myFiles.toString());
            String s = JSON.toJSONString(myFiles);
            return s;
        }else {
            return "false";
        }
    }

    @GetMapping("deletefilefile/{myFileId}")
    public String deleteFileFile(@PathVariable("myFileId") Integer myFileId){
        if (myFileService.deleteFileByMyFileId(myFileId)){
            return "shanchu chenggong";
        }else {
            return "false";
        }
    }

    @GetMapping("updatefilefile")
    public String updatefilefile(@RequestParam("fName") String myFileName){
        MyFile file = MyFile.builder().myFileId(1).myFileName(myFileName).build();
        if (myFileService.updateFile(file)){
            List<MyFile> myFiles = myFileService.queryFileAnd(file);
            System.out.println("xiugai成功"+myFiles.toString());
            String s = JSON.toJSONString(myFiles);
            return s;
        }else {
            return "false";
        }

    }

}
