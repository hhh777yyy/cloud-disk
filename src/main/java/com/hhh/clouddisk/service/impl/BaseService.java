package com.hhh.clouddisk.service.impl;

import com.hhh.clouddisk.mapper.FileFolderMapper;
import com.hhh.clouddisk.mapper.FileStoreMapper;
import com.hhh.clouddisk.mapper.MyFileMapper;
import com.hhh.clouddisk.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class BaseService {

    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected MyFileMapper myFileMapper;
    @Autowired
    protected FileFolderMapper fileFolderMapper;
    @Autowired
    protected FileStoreMapper fileStoreMapper;
}
