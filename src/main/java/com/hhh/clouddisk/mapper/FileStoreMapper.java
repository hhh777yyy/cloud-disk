package com.hhh.clouddisk.mapper;

import com.hhh.clouddisk.entity.FileStore;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileStoreMapper {

    Boolean insertFileStore(int userId);

    Boolean delteFileStoreByFileStoreId(int fileStoreId);

    Boolean delteFileStoreByUserId(int userId);

    Boolean addSize(int fileStoreId,double size);

    Boolean subSize(int fileStoreId,double size);

    FileStore queryFileStoreByUserId(int userId);

    FileStore queryFileStoreByFileStoreId(int fileStoreId);

}
