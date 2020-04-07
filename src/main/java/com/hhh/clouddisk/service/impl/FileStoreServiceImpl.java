package com.hhh.clouddisk.service.impl;

import com.hhh.clouddisk.entity.FileStore;
import com.hhh.clouddisk.entity.User;
import com.hhh.clouddisk.mapper.FileStoreMapper;
import com.hhh.clouddisk.mapper.UserMapper;
import com.hhh.clouddisk.service.FileStoreService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileStoreServiceImpl extends BaseService implements FileStoreService {

    /**
     * 添加文件仓库
     **/
    @Override
    public Boolean insertFileStore(int userId) {
        if(fileStoreMapper.queryFileStoreByUserId(userId) == null){
            return (fileStoreMapper.insertFileStore(userId));
        }else {
            return false;
        }
    }

    /**
     * 通过fileStoreId删除文件仓库
     **/
    @Override
    public Boolean delteFileStoreByFileStoreId(int fileStoreId) {
        if(fileStoreMapper.queryFileStoreByFileStoreId(fileStoreId) != null){
            return (fileStoreMapper.delteFileStoreByFileStoreId(fileStoreId));
        }else {
            return false;
        }
    }

    /**
     * 通过userId删除文件仓库
     **/
    @Override
    public Boolean delteFileStoreByUserId(int userId) {
        if(fileStoreMapper.queryFileStoreByUserId(userId) != null){
            return (fileStoreMapper.delteFileStoreByUserId(userId));
        }else {
            return false;
        }
    }

    /**
     * 修改仓库当前已使用的容量
     **/
    @Override
    public Boolean addSize(int fileStoreId, double size) {
        if(fileStoreMapper.queryFileStoreByFileStoreId(fileStoreId) != null){
            return (fileStoreMapper.addSize(fileStoreId,size));
        }else {
            return false;
        }
    }

    /**
     * 修改仓库当前已使用的容量
     **/
    @Override
    public Boolean subSize(int fileStoreId, double size) {
        if(fileStoreMapper.queryFileStoreByFileStoreId(fileStoreId) != null){
            return (fileStoreMapper.subSize(fileStoreId,size));
        }else {
            return false;
        }
    }

    /**
     * 根据用户id获得文件仓库
     **/
    @Override
    public FileStore queryFileStoreByUserId(int userId) {
        return fileStoreMapper.queryFileStoreByUserId(userId);
    }

    /**
     * 根据文件仓库id获得文件仓库
     **/
    @Override
    public FileStore queryFileStoreByFileStoreId(int fileStoreId) {
        return fileStoreMapper.queryFileStoreByFileStoreId(fileStoreId);
    }
}
