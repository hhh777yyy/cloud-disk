package com.hhh.clouddisk.controller;

import com.hhh.clouddisk.entity.FileFolder;
import com.hhh.clouddisk.entity.MyFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FileStoreController extends  BaseController{

    /**
     * 上传文件
     */
    @PostMapping("/uploadFile")
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile files) {
        Map<String, Object> map = new HashMap<String, Object>();
        return map;
    }

    /**
     * 下载文件
     */
    @GetMapping("/downloadFile")
    public void downloadFile(@RequestParam Integer fId){
        Map<String, Object> map = new HashMap<String, Object>();
        return ;
    }

    /**
     * 删除文件
     */
    @GetMapping("/deleteFile")
    public String deleteFile(@RequestParam Integer fId,Integer folder){
        Map<String, Object> map = new HashMap<String, Object>();
        return "";
    }

    /**
     * 删除文件夹并清空文件
     */
    @GetMapping("/deleteFolder")
    public String deleteFolder(@RequestParam Integer fId){
        Map<String, Object> map = new HashMap<String, Object>();
        return "";
    }

    /**
     * 添加文件夹
     */
    @PostMapping("/addFolder")
    public String addFolder(FileFolder folder, Map<String, Object> map) {
        return "";
    }

    /**
     * 重命名文件夹
     */
    @PostMapping("/updateFolder")
    public String updateFolder(FileFolder folder,Map<String, Object> map) {
        return "";
    }

    /**
     * 重命名文件
     */
    @PostMapping("/updateFileName")
    public String updateFileName(MyFile file, Map<String, Object> map) {
        return "";
    }

    /**
     * 分享文件
     */
    @GetMapping("/file/share")
    public void shareFile(Integer f,String p,String t){
        return ;
    }

}
