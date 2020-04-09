package com.hhh.clouddisk.controller;

import com.hhh.clouddisk.entity.FileFolder;
import com.hhh.clouddisk.entity.FileStoreStatistics;
import com.hhh.clouddisk.entity.MyFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class SystemController extends  BaseController{
    /**
     * 前往网盘文件主页
     */
    @GetMapping("/files")
    public String toFileStorePage(Integer fId,
                                  String fName,
                                  Integer error,
                                  Map<String, Object> map) {

        //判断是否包含错误信息
        if (error != null) {
            if (error == 1) {
                map.put("error", "添加失败！当前已存在同名文件夹");
            }
            if (error == 2) {
                map.put("error", "重命名失败！文件夹已存在");
            }
        }

        List<FileFolder> folders;
//        当前目录含有的文件夹
        List<MyFile> files;
//        当前目录含有的文件
        List<FileFolder> location = new ArrayList<>();
//        当前的目录路径
        FileFolder nowFolder;
//        当前文件夹的信息（fileFolderId、fileFolderName之类）

        if (fId == null || fId==0){
//            代表当前为根目录
            fId = 0;
            folders = fileFolderService.queryFileFolderByParentFolderId(fId);
            files = myFileService.queryFileByParentFolderId(fId);
            nowFolder = FileFolder.builder().fileFolderId(fId).build();
            location.add(nowFolder);

        }else {
//            当前的具体目录
            folders = fileFolderService.queryFileFolderByParentFolderId(fId);
            files = myFileService.queryFileByParentFolderId(fId);
            nowFolder = fileFolderService.queryFileFolderByFileFolderId(fId);
            FileFolder temp = nowFolder;
            while (temp.getParentFolderId() != 0){
                temp = fileFolderService.queryFileFolderByFileFolderId(temp.getFileFolderId());
                location.add(temp);
            }
        }
        FileStoreStatistics statistics = myFileService.getCountStatistics(loginUser.getFileStoreId());

        map.put("statistics", statistics);
        map.put("folders", folders);
        map.put("files", files);
        map.put("nowFolder", nowFolder);
        map.put("location", location);
//        logger.info("网盘页面域中的数据:" + map);
        return "u-admin/files";
    }

    /**
     * 前往文件上传页面
     */
    @GetMapping("/upload")
    public String toUploadPage(Integer fId, String fName, Map<String, Object> map) {

        List<FileFolder> folders;
//        当前目录含有的文件夹
        List<FileFolder> location = new ArrayList<>();
//        当前的目录路径
        FileFolder nowFolder;
//        当前文件夹的信息（fileFolderId、fileFolderName之类）

        if (fId == null || fId==0){
//            代表当前为根目录
            fId = 0;
            folders = fileFolderService.queryFileFolderByParentFolderId(fId);
            nowFolder = FileFolder.builder().fileFolderId(fId).build();
            location.add(nowFolder);

        }else {
//            当前的具体目录
            folders = fileFolderService.queryFileFolderByParentFolderId(fId);
            nowFolder = fileFolderService.queryFileFolderByFileFolderId(fId);
            FileFolder temp = nowFolder;
            while (temp.getParentFolderId() != 0){
                temp = fileFolderService.queryFileFolderByFileFolderId(temp.getFileFolderId());
                location.add(temp);
            }
        }
        FileStoreStatistics statistics = myFileService.getCountStatistics(loginUser.getFileStoreId());

        map.put("statistics", statistics);
        map.put("folders", folders);
        map.put("nowFolder", nowFolder);
        map.put("location", location);
//        logger.info("网盘页面域中的数据:" + map);
        return "u-admin/upload";
    }

    /**
     * 前往所有文档页面
     */
    @GetMapping("/doc-files")
    public String toDocFilePage( Map<String, Object> map) {
        List<MyFile> files = myFileService.queryFileByType(loginUser.getFileStoreId(),1);
        FileStoreStatistics statistics = myFileService.getCountStatistics(loginUser.getFileStoreId());
        map.put("statistics", statistics);
        map.put("files", files);
        return "u-admin/doc-files";
    }

    /**
     * 前往所有图像页面
     */
    @GetMapping("/image-files")
    public String toImageFilePage( Map<String, Object> map) {
        List<MyFile> files = myFileService.queryFileByType(loginUser.getFileStoreId(),2);
        FileStoreStatistics statistics = myFileService.getCountStatistics(loginUser.getFileStoreId());
        map.put("statistics", statistics);
        map.put("files", files);
        return "u-admin/image-files";
    }

    /**
     * 前往所有视频页面
     */
    @GetMapping("/video-files")
    public String toVideoFilePage( Map<String, Object> map) {
        List<MyFile> files = myFileService.queryFileByType(loginUser.getFileStoreId(),3);
        FileStoreStatistics statistics = myFileService.getCountStatistics(loginUser.getFileStoreId());
        map.put("statistics", statistics);
        map.put("files", files);
        return "u-admin/video-files";
    }

    /**
     * 前往所有音频页面
     */
    @GetMapping("/music-files")
    public String toMusicFilePage( Map<String, Object> map) {
        List<MyFile> files = myFileService.queryFileByType(loginUser.getFileStoreId(),4);
        FileStoreStatistics statistics = myFileService.getCountStatistics(loginUser.getFileStoreId());
        map.put("statistics", statistics);
        map.put("files", files);
        return "u-admin/music-files";
    }

    /**
     * 前往其他文件页面
     */
    @GetMapping("/other-files")
    public String toOtherFilePage( Map<String, Object> map) {
        List<MyFile> files = myFileService.queryFileByType(loginUser.getFileStoreId(),5);
        FileStoreStatistics statistics = myFileService.getCountStatistics(loginUser.getFileStoreId());
        map.put("statistics", statistics);
        map.put("files", files);
        return "u-admin/music-files";
    }

    /**
     * 前往登录之后的用户主页
     */
    @GetMapping("/index")
    public String index(Map<String, Object> map) {
        FileStoreStatistics statistics = myFileService.getCountStatistics(loginUser.getFileStoreId());
        statistics.setFileStore(fileStoreService.queryFileStoreByFileStoreId(loginUser.getFileStoreId()));
        map.put("statistics", statistics);
        return "u-admin/index";
    }

    /**
     * 前往帮助页面
     */
    @GetMapping("/help")
    public String helpPage(Map<String, Object> map) {
        FileStoreStatistics statistics = myFileService.getCountStatistics(loginUser.getFileStoreId());
        map.put("statistics", statistics);
        return "u-admin/help";
    }
}
