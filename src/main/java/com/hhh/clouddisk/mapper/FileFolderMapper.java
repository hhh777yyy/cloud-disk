package com.hhh.clouddisk.mapper;

import com.hhh.clouddisk.entity.FileFolder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileFolderMapper {

    /**
     * 增加文件夹
     **/
    Boolean insertFileFolder(FileFolder fileFolder);

    /**
     * 根据文件夹的id修改文件夹信息
     **/
    Boolean deleteFileFolderById(int fileFolderId);

    /**
     * 修改文件夹属性
     **/
    Boolean updateFileFolderById(FileFolder fileFolder);

    /**
     * 根据文件夹的id获取文件夹
     **/
    FileFolder queryFileFolderByFileFolderId(int fileFolderId);

    /**
     * 根据文件夹的Name获取文件夹
     **/
    FileFolder queryFileFolderByFileFolderName(String fileFolderName);

    /**
     * 根据父文件夹的id获取文件夹
     **/
    List<FileFolder> queryFileFolderByParentFolderId(int parentFolderId);

    /**
     * 根据仓库的id获取文件夹
     **/
    List<FileFolder> queryFileFolderByFileStoreId(int fileStoreId);

    /**
     * 父文件夹id删除文件夹
     **/
    Boolean deleteFileFolderByParentFolderId(int parentFolderId);

    /**
     * 根据仓库的id删除文件夹
     **/
    Boolean deleteFileFolderByFileStoreId(int fileStoreId);

    /**
     * 获得仓库的文件夹数量
     **/
    Integer queryFileFolderCountByFileStoreId(int fileStoreId);

    /**
     * 根据仓库Id获得仓库根目录下的所有文件夹
     **/
    List<FileFolder> queryRootFileFolderByFileStoreId(int fileStoreId);

//      根据父文件夹id删除文件夹
//    根据仓库的id删除文件夹
//    获得仓库的文件夹数量
//    根据仓库Id获得仓库根目录下的所有文件夹
//    根据文件夹的id获取文件夹下面的文件(myfilemapper中有类似的方法）

//    fileFolderId;
//    fileFolderName;
//    parentFolderId
//    fileStoreId
//    time
}
