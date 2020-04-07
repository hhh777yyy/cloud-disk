package com.hhh.clouddisk.service;

import com.hhh.clouddisk.entity.User;

import java.util.List;

public interface UserService {

//    增删改查
    Boolean insertUser(User user);

    Boolean deteleUserByOpenId(String openId);

    Boolean updateUser(User user);

    List<User> queryAllUsers();

    User queryUserByOpenId(String openId);

    User queryUserByUserId(int userId);
}
