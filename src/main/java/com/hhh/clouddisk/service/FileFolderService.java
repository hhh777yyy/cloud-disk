package com.hhh.clouddisk.service;

import com.hhh.clouddisk.entity.FileFolder;

import java.util.List;

public interface FileFolderService {

    Boolean insertFileFolder(FileFolder fileFolder);

    Boolean deleteFileFolderById(int fileFolderId);

    Boolean updateFileFolderById(FileFolder fileFolder);

    List<FileFolder> queryAllFileFolders();

    FileFolder queryFileFolderByFileFolderId(int fileFolderId);

    FileFolder queryFileFolderByFileFolderName(String fileFolderName);

    List<FileFolder> queryFileFolderByParentFolderId(int parentFolderId);

    List<FileFolder> queryFileFolderByFileStoreId(int fileStoreId);
}
