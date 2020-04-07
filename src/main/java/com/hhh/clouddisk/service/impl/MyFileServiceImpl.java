package com.hhh.clouddisk.service.impl;

import com.hhh.clouddisk.entity.MyFile;
import com.hhh.clouddisk.service.MyFileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyFileServiceImpl extends BaseService implements MyFileService {

    /**
     * 增加文件
     **/
    @Override
    public Boolean insertFile(MyFile myFile) {
        if (isExist(myFile.getMyFileName())){
            return false;
        }else {
            return myFileMapper.insertFile(myFile);
        }
    }

    /**
     * 根据文件的id删除文件
     **/
    @Override
    public Boolean deleteFileByMyFileId(int myFileId) {
        if (isExist(myFileId)){
            return myFileMapper.deleteFileByMyFileId(myFileId);
        }else {
            return false;
        }
    }

    /**
     * 根据文件的id修改文件
     **/
    @Override
    public Boolean updateFile(MyFile myFile) {
        if (isExist(myFile.getMyFileId())){
            return myFileMapper.updateFile(myFile);
        }else {
            return false;
        }
    }

    /**
     * 根据父文件夹的id删除文件
     **/
    @Override
    public Boolean deleteFileByParentFolderId(int parentFolderId) {
        if (fileFolderMapper.queryFileFolderByFileFolderId(parentFolderId) != null){
            return myFileMapper.deleteFileByParentFolderId(parentFolderId);
        }else {
            return false;
        }
    }

    /**
     * 根据文件的id获取文件
     **/
    @Override
    public MyFile queryFileByMyFileId(int myFileId) {
        return myFileMapper.queryFileByMyFileId(myFileId);
    }

    /**
     * 根据文件的Name获取文件
     **/
    @Override
    public MyFile queryFileByMyFileName(String myFileName) {
        return myFileMapper.queryFileByMyFileName(myFileName);
    }

    /**
     * 获得仓库根目录下的所有文件
     **/
    @Override
    public List<MyFile> queryRootFileByFileStoreId(int fileStoreId) {
        return myFileMapper.queryRootFileByFileStoreId(fileStoreId);
    }

    /**
     * 根据父文件夹id获得文件
     **/
    @Override
    public List<MyFile> queryFileByParentFolderId(int parentFolderId) {
        return myFileMapper.queryFileByParentFolderId(parentFolderId);
    }

    /**
     * 根据类别获取文件
     **/
    @Override
    public List<MyFile> queryFileByType(int fileStoreId, int type) {
        return myFileMapper.queryFileByType(fileStoreId,type);
    }

    private Boolean isExist(String myFileName){
        if (myFileMapper.queryFileByMyFileName(myFileName)  == null){
            return false;
        }else {
            return true;
        }
    }

    private Boolean isExist(int myFileId){
        if (myFileMapper.queryFileByMyFileId(myFileId) == null){
            return false;
        }else {
            return true;
        }
    }
}
