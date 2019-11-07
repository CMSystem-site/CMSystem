package cn.lightina.managebooks.service;

import cn.lightina.managebooks.pojo.User;


public interface UserService {
    User checkUser(User user);

    User checkManager(User user);

    Integer addUser(User user);

    Integer updatePwd(User user);

    String findPwd(String phone);
}
