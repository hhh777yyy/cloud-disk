package com.hhh.clouddisk.mapper;

import com.hhh.clouddisk.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    public List<User> queryAllUsers();

    public User queryUserByOpenId(String openId);

    public User queryUserByUserId(int userId);

    public Boolean deleteUserByOpenId(String openId);

    public Boolean insertUser(User user);

    public Boolean updateUser(User user);

}
