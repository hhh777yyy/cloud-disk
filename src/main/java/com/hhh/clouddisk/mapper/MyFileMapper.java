package com.hhh.clouddisk.mapper;

import com.hhh.clouddisk.entity.FileStoreStatistics;
import com.hhh.clouddisk.entity.MyFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyFileMapper {

    /*
     * 添加文件
     */
    Boolean insertFile(MyFile myFile);

    /*
     * 根据文件的id删除文件
     */
    Boolean deleteFileByMyFileId(int myFileId);

    /**
     * 根据文件id修改文件
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
     * 根据文件的id获取文件
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
    FileStoreStatistics getCountStatistics(int id);
    /**
     * List<MyFile> queryFileOr(MyFile myFile);
     * List<MyFile> queryFileAnd(MyFile myFile);
     **/


    //还要添加queryFileById……

//    根据父文件夹的id删除文件
//    根据文件的id获取文件
//    获得仓库根目录下的所有文件
//    根据父文件夹id获得文件
//    根据类别获取文件
//    获取仓库的统计信息
}
