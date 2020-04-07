package com.hhh.clouddisk.service.impl;

import com.hhh.clouddisk.entity.User;
import com.hhh.clouddisk.mapper.UserMapper;
import com.hhh.clouddisk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl extends BaseService implements UserService {
    /*
     * 添加user
     */
    @Override
    public Boolean insertUser(User user) {
        if(isExist(user.getUserId())){
            return false;
//            若存在此人，返回false
        }else {
            if (userMapper.insertUser(user)){
                return true;
//                若不存在此人，运行insert语句，成功的话，返回true
            }else {
                return false;
            }
        }
    }

    /*
     * 删除user
     */
    @Override
    public Boolean deteleUserByOpenId(String openId) {
        if (isExist(openId)){
            if (userMapper.deleteUserByOpenId(openId)){
                return true;
//                若存在此人，运行delete语句，成功的话，返回true
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /*
     * 更新user数据
     */
    @Override
    public Boolean updateUser(User user) {
        if(isExist(user.getUserId())){
            if (userMapper.updateUser(user)){
                return true;
//                若存在此人，运行update语句，成功的话，返回true
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /*
     * 查询全部user
     */
    @Override
    public List<User> queryAllUsers() {
        return userMapper.queryAllUsers();
    }

    /*
     * 根据openId查询user
     */
    @Override
    public User queryUserByOpenId(String openId) {
        return userMapper.queryUserByOpenId(openId);
    }

    /*
     * 根据userId查询user
     */
    @Override
    public User queryUserByUserId(int userId) {
        return userMapper.queryUserByUserId(userId);
    }

    /*
     * 根据userId判断此人是否存在
     */
    private Boolean isExist(int userId){
        Boolean orNot = null;
        List<User> users = queryAllUsers();
        for (int i=0;i<users.size();i++){
            if (users.get(i).getUserId().equals(userId)){
                orNot = true;
            }else{
                orNot = false;
            }
        }
        return orNot;
    }

    /*
     * 根据openId判断此人是否存在
     */
    private Boolean isExist(String openId){
        Boolean orNot = null;
        List<User> users = queryAllUsers();
        for (int i=0;i<users.size();i++){
            if (users.get(i).getOpenId().equals(openId)){
                orNot = true;
            }else{
                orNot = false;
            }
        }
        return orNot;
    }
}
