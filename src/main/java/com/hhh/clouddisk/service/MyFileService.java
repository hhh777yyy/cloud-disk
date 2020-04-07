package com.hhh.clouddisk.service;

import com.hhh.clouddisk.entity.MyFile;

import java.util.List;

public interface MyFileService {

    Boolean insertFile(MyFile myFile);

    Boolean deleteFileByMyFileId(int myFileId);

    Boolean updateFile(MyFile myFile);

    List<MyFile> queryFileAnd(MyFile myFile);

    List<MyFile> queryFileOr(MyFile myFile);
}
