package com.hhh.clouddisk.service.impl;

import com.hhh.clouddisk.entity.MyFile;
import com.hhh.clouddisk.service.MyFileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyFileServiceImpl extends BaseService implements MyFileService {
    @Override
    public Boolean insertFile(MyFile myFile) {
        if (isExist(myFile.getMyFileName())){
            return false;
        }else {
            return myFileMapper.insertFile(myFile);
        }
    }

    @Override
    public Boolean deleteFileByMyFileId(int myFileId) {
        if (isExist(myFileId)){
            return myFileMapper.deleteFileByMyFileId(myFileId);
        }else {
            return false;
        }
    }

    @Override
    public Boolean updateFile(MyFile myFile) {
        if (isExist(myFile.getMyFileId())){
            return myFileMapper.updateFile(myFile);
        }else {
            return false;
        }
    }

    @Override
    public List<MyFile> queryFileAnd(MyFile myFile) {
        return myFileMapper.queryFileAnd(myFile);
    }

    @Override
    public List<MyFile> queryFileOr(MyFile myFile) {
        return myFileMapper.queryFileOr(myFile);
    }

    private Boolean isExist(String myFileName){
        MyFile myfile = MyFile.builder().myFileName(myFileName).build();
        List<MyFile> myFiles = myFileMapper.queryFileAnd(myfile);
        if (myFiles != null && !myFiles.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    private Boolean isExist(int myFileId){
        MyFile myfile = MyFile.builder().myFileId(myFileId).build();
        if (myFileMapper.queryFileAnd(myfile) == null){
            return false;
        }else {
            return true;
        }
    }
}
