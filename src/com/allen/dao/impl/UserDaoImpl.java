package com.allen.dao.impl;

import com.allen.dao.UserDao;
import com.allen.pojo.User;


/**
 * @author Allen
 * @date 2020/9/16 9:54
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username=?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`email`from t_user where username=? and password=?";
        return queryForOne(User.class,sql,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "INSERT INTO t_user(`username`,`password`,`email`)VALUES(?,?,?);\n";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
