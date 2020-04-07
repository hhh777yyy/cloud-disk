package com.hhh.clouddisk.mapper;

import com.hhh.clouddisk.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 查询全部数据
     **/
    public List<User> queryAllUsers();

    /**
     * 通过openID查询单条数据
     **/
    public User queryUserByOpenId(String openId);

    /**
     * 通过userId查询单条数据
     **/
    public User queryUserByUserId(int userId);

    /**
     * 通过openID删除User
     **/
    public Boolean deleteUserByOpenId(String openId);

    /**
     * 修改User
     **/
    public Boolean insertUser(User user);

    /**
     * 删除User
     **/
    public Boolean updateUser(User user);

    //实体作为筛选条件查询数据

}
