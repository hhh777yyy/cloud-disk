package com.hhh.clouddisk.mapper;

import com.hhh.clouddisk.entity.FileStore;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileStoreMapper {

    /**
     * 添加文件仓库
     **/
    Boolean insertFileStore(int userId);

    /**
     * 通过fileStoreId删除文件仓库
     **/
    Boolean delteFileStoreByFileStoreId(int fileStoreId);

    /**
     * 通过userId删除文件仓库
     **/
    Boolean delteFileStoreByUserId(int userId);

    /**
     * 修改仓库当前已使用的容量
     **/
    Boolean addSize(int fileStoreId,double size);

    /**
     * 修改仓库当前已使用的容量
     **/
    Boolean subSize(int fileStoreId,double size);

    /**
     * 根据用户id获得文件仓库
     **/
    FileStore queryFileStoreByUserId(int userId);

    /**
     * 根据文件仓库id获得文件仓库
     **/
    FileStore queryFileStoreByFileStoreId(int fileStoreId);

}
