package com.allen.service.impl;

import com.allen.dao.UserDao;
import com.allen.dao.impl.UserDaoImpl;
import com.allen.pojo.User;
import com.allen.service.UserService;

/**
 * @author Allen
 * @date 2020/9/16 10:08
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        return userDao.queryUserByUsername(username)==null?false:true;
    }
}
