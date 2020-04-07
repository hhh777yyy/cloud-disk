package com.hhh.clouddisk.service;

import com.hhh.clouddisk.entity.FileStore;

public interface FileStoreService {

    Boolean insertFileStore(int userId);

    Boolean delteFileStoreByFileStoreId(int fileStoreId);

    Boolean delteFileStoreByUserId(int userId);

    Boolean addSize(int fileStoreId,double size);

    Boolean subSize(int fileStoreId,double size);

    FileStore queryFileStoreByUserId(int userId);

    FileStore queryFileStoreByFileStoreId(int fileStoreId);
}
