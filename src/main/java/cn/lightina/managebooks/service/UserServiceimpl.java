package cn.lightina.managebooks.service;

import cn.lightina.managebooks.dao.UserMapper;
import cn.lightina.managebooks.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {
    @Autowired(required = false)
    UserMapper userMapper;

    @Override
    public User checkUser(User user) {
        return userMapper.checkUser(user);
    }

    @Override
    public User checkManager(User user) {
        return userMapper.checkManager(user);
    }

    @Override
    public User findByUserName(User user) {
        return userMapper.findByUserName(user);
    }

    @Override
    public Integer addUser(User user) {
        if(userMapper.findByUserName(user)!=null)
            return 0;
        return userMapper.addUser(user);
    }

    @Override
    public Integer updatePwd(User user) {
        return userMapper.updatePwd(user);
    }

    @Override
    public String findPwd(String phone) {
        return userMapper.findPwd(phone);
    }

    @Override
    public User checkPhone(String phone) {
        return userMapper.checkPhone(phone);
    }

    @Override
    public Integer getUserNum() {
        return userMapper.getUserNum();
    }

}
