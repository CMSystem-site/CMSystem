package cn.lightina.managebooks.dao;

import cn.lightina.managebooks.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    //通过username和passwd 验证用户
    User checkUser(@Param("user") User user);

    User checkManager(@Param("user") User user);

    //增加用户
    Integer addUser(@Param("user") User user);

    //通过userName更新密码
    Integer updatePwd(@Param("user")User user);

    //通过手机号码找回密码
    String findPwd(@Param("phone")String phone);

    //通过姓名查找用户
    User findByUserName(@Param("user")User user);

    //通过手机号码查找用户
    User checkPhone(@Param("phone")String phone);
}
