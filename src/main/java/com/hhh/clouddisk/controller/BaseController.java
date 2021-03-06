package com.hhh.clouddisk.controller;

import com.hhh.clouddisk.entity.User;
import com.hhh.clouddisk.service.FileFolderService;
import com.hhh.clouddisk.service.FileStoreService;
import com.hhh.clouddisk.service.MyFileService;
import com.hhh.clouddisk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {

    @Autowired
    protected UserService userService;
    @Autowired
    protected MyFileService myFileService;
    @Autowired
    protected FileFolderService fileFolderService;
    @Autowired
    protected FileStoreService fileStoreService;

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected User loginUser;

    /**
     * 在每个子类方法调用之前先调用
     * 设置request,response,session这三个对象
     *
     * @param request
     * @param response
     */
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession(true);
        loginUser = (User) session.getAttribute("loginUser");
    }
}
