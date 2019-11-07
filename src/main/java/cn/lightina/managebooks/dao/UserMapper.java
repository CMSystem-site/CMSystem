package cn.lightina.managebooks.dao;

import cn.lightina.managebooks.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    //通过username和passwd 验证用户 reader
    User checkUser(@Param("user") User user);
    User checkManager(@Param("user") User user);
    Integer addUser(@Param("user") User user);
    Integer updatePwd(@Param("user")User user);
    String findPwd(@Param("phone")String phone);
    User findByUserName(@Param("user")User user);
}
