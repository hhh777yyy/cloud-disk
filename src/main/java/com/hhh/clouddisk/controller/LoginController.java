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



}
