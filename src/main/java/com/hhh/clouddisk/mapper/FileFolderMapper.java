package com.hhh.clouddisk.mapper;

import com.hhh.clouddisk.entity.FileFolder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileFolderMapper {

    Boolean insertFileFolder(FileFolder fileFolder);

    Boolean deleteFileFolderById(int fileFolderId);

    Boolean updateFileFolderById(FileFolder fileFolder);

    List<FileFolder> queryAllFileFolders();

    FileFolder queryFileFolderByFileFolderId(int fileFolderId);

    FileFolder queryFileFolderByFileFolderName(String fileFolderName);

    List<FileFolder> queryFileFolderByParentFolderId(int parentFolderId);

    List<FileFolder> queryFileFolderByFileStoreId(int fileStoreId);



//    fileFolderId;
//    fileFolderName;
//    parentFolderId
//    fileStoreId
//    time
}
