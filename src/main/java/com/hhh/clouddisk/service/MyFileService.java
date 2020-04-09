package com.hhh.clouddisk.service;

import com.hhh.clouddisk.entity.FileStoreStatistics;
import com.hhh.clouddisk.entity.MyFile;

import java.util.List;

public interface MyFileService {
    /**
     * 增加文件
     **/
    Boolean insertFile(MyFile myFile);

    /**
     * 根据文件的id删除文件
     **/
    Boolean deleteFileByMyFileId(int myFileId);

    /**
     * 根据文件的id修改文件
     **/
    Boolean updateFile(MyFile myFile);

    /**
     * 根据父文件夹的id删除文件
     **/
    Boolean deleteFileByParentFolderId(int parentFolderId);

    /**
     * 根据文件的id获取文件
     **/
    MyFile queryFileByMyFileId(int myFileId);

    /**
     * 根据文件的Name获取文件
     **/
    MyFile queryFileByMyFileName(String myFileName);

    /**
     * 获得仓库根目录下的所有文件
     **/
    List<MyFile> queryRootFileByFileStoreId(int fileStoreId);

    /**
     * 根据父文件夹id获得文件
     **/
    List<MyFile> queryFileByParentFolderId(int parentFolderId);

    /**
     * 根据类别获取文件
     **/
    List<MyFile> queryFileByType(int fileStoreId,int type);

    /**
     * 获取仓库的统计信息
     **/
    FileStoreStatistics getCountStatistics(int fileStoreId);


}
