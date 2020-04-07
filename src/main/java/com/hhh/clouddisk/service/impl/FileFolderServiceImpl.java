package com.hhh.clouddisk.service.impl;

import com.hhh.clouddisk.entity.FileFolder;
import com.hhh.clouddisk.service.FileFolderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileFolderServiceImpl extends BaseService implements FileFolderService {
    @Override
    public Boolean insertFileFolder(FileFolder fileFolder) {
        if (isExist(fileFolder.getFileFolderName())){
            return false;
        }else {
            return fileFolderMapper.insertFileFolder(fileFolder);
        }
    }

    @Override
    public Boolean deleteFileFolderById(int fileFolderId) {
        if (isExist(fileFolderId)){
            return fileFolderMapper.deleteFileFolderById(fileFolderId);
        }else {
            return false;
        }
    }

    @Override
    public Boolean updateFileFolderById(FileFolder fileFolder) {
        if (isExist(fileFolder.getFileFolderId())==true ){
            return fileFolderMapper.updateFileFolderById(fileFolder);
        }else {
            return false;
        }
    }

    @Override
    public List<FileFolder> queryAllFileFolders() {
        return fileFolderMapper.queryAllFileFolders();
    }

    @Override
    public FileFolder queryFileFolderByFileFolderId(int fileFolderId) {
        return fileFolderMapper.queryFileFolderByFileFolderId(fileFolderId);
    }

    @Override
    public FileFolder queryFileFolderByFileFolderName(String fileFolderName) {
        return fileFolderMapper.queryFileFolderByFileFolderName(fileFolderName);
    }

    @Override
    public List<FileFolder> queryFileFolderByParentFolderId(int parentFolderId) {
        return fileFolderMapper.queryFileFolderByParentFolderId(parentFolderId);
    }

    @Override
    public List<FileFolder> queryFileFolderByFileStoreId(int fileStoreId) {
        return fileFolderMapper.queryFileFolderByFileStoreId(fileStoreId);
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
