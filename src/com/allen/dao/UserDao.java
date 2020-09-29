package com.allen.dao;

import com.allen.pojo.User;

/**
 * @author Allen
 * @date 2020/9/16 9:52
 */
public interface UserDao {
    public User queryUserByUsername(String username);

    public User queryUserByUsernameAndPassword(String username, String password);

    public int saveUser(User user);
}
