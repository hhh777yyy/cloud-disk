package com.hhh.clouddisk.mapper;

import com.hhh.clouddisk.entity.MyFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyFileMapper {

    Boolean insertFile(MyFile myFile);

    Boolean deleteFileByMyFileId(int myFileId);

    Boolean updateFile(MyFile myFile);

    List<MyFile> queryFileAnd(MyFile myFile);

    List<MyFile> queryFileOr(MyFile myFile);

    //还要添加queryFileById……

}
