package com.allen.service;

import com.allen.pojo.User;

/**
 * @author Allen
 * @date 2020/9/16 10:06
 */
public interface UserService {
    public void registUser(User user);

    public User login(User user);

    public boolean existsUsername(String username);

}
