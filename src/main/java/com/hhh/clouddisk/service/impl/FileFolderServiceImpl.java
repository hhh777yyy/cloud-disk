package com.hhh.clouddisk.service.impl;

import com.hhh.clouddisk.entity.FileFolder;
import com.hhh.clouddisk.service.FileFolderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileFolderServiceImpl extends BaseService implements FileFolderService {
    /**
     * 增加文件夹
     **/
    @Override
    public Boolean insertFileFolder(FileFolder fileFolder) {
        if (isExist(fileFolder.getFileFolderName())){
            return false;
        }else {
            return fileFolderMapper.insertFileFolder(fileFolder);
        }
    }
    /**
     * 根据文件夹的id修改文件夹信息
     **/
    @Override
    public Boolean deleteFileFolderById(int fileFolderId) {
        if (isExist(fileFolderId)){
            return fileFolderMapper.deleteFileFolderById(fileFolderId);
        }else {
            return false;
        }
    }
    /**
     * 修改文件夹属性
     **/
    @Override
    public Boolean updateFileFolderById(FileFolder fileFolder) {
        if (isExist(fileFolder.getFileFolderId())==true ){
            return fileFolderMapper.updateFileFolderById(fileFolder);
        }else {
            return false;
        }
    }
    /**
     * 根据文件夹的id获取文件夹
     **/
    @Override
    public FileFolder queryFileFolderByFileFolderId(int fileFolderId) {
        return fileFolderMapper.queryFileFolderByFileFolderId(fileFolderId);
    }
    /**
     * 根据文件夹的Name获取文件夹
     **/
    @Override
    public FileFolder queryFileFolderByFileFolderName(String fileFolderName) {
        return fileFolderMapper.queryFileFolderByFileFolderName(fileFolderName);
    }
    /**
     * 根据父文件夹的id获取文件夹
     **/
    @Override
    public List<FileFolder> queryFileFolderByParentFolderId(int parentFolderId) {
        return fileFolderMapper.queryFileFolderByParentFolderId(parentFolderId);
    }
    /**
     * 根据仓库的id获取文件夹
     **/
    @Override
    public List<FileFolder> queryFileFolderByFileStoreId(int fileStoreId) {
        return fileFolderMapper.queryFileFolderByFileStoreId(fileStoreId);
    }
    /**
     * 根据父文件夹id删除文件夹
     **/
    @Override
    public Boolean deleteFileFolderByParentFolderId(int parentFolderId) {
        if (isExist(parentFolderId)){
            return fileFolderMapper.deleteFileFolderByParentFolderId(parentFolderId);
        }else {
            return false;
        }
    }
    /**
     * 根据仓库的id删除文件夹
     **/
    @Override
    public Boolean deleteFileFolderByFileStoreId(int fileStoreId) {
        if (fileStoreMapper.queryFileStoreByFileStoreId(fileStoreId) != null){
            return fileFolderMapper.deleteFileFolderByFileStoreId(fileStoreId);
        }else {
            return false;
        }
    }
    /**
     * 获得仓库的文件夹数量
     **/
    @Override
    public Integer queryFileFolderCountByFileStoreId(int fileStoreId) {
        return fileFolderMapper.queryFileFolderCountByFileStoreId(fileStoreId);
    }
    /**
     * 根据仓库Id获得仓库根目录下的所有文件夹
     **/
    @Override
    public List<FileFolder> queryRootFileFolderByFileStoreId(int fileStoreId) {
        return fileFolderMapper.queryRootFileFolderByFileStoreId(fileStoreId);
    }

    private Boolean isExist(String fileFolderName){
        if (fileFolderMapper.queryFileFolderByFileFolderName(fileFolderName) != null){
            return true;
        }else{
            return false;
        }
    }

    private Boolean isExist(int fileFolderId){
        if (fileFolderMapper.queryFileFolderByFileFolderId(fileFolderId) != null){
            return true;
        }else{
            return false;
        }
    }
}
