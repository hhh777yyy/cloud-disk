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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginController extends  BaseController{

    private Logger logger = LoggerFactory.getLogger(LoginController.class);


    @GetMapping("/admin")
    public String adminLogin() {
        User user = userService.queryUserByOpenId("1");
        logger.info("admin用户登录成功！" + user);
        session.setAttribute("loginUser", user);
        return "redirect:/index";
    }


}
