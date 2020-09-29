package com.allen.test;

import com.allen.dao.UserDao;
import com.allen.dao.impl.UserDaoImpl;
import org.junit.Test;
import com.allen.pojo.User;

/**
 * @author Allen
 * @date 2020/9/16 9:56
 */
public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername(){

        if(userDao.queryUserByUsername("admin")==null){
            System.out.println("用户名可用");
        }else{
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword(){

        if(userDao.queryUserByUsernameAndPassword("admin","admin")==null){
            System.out.println("get user info failed! plz try again");
        }else{
            System.out.println("success!");
        }
    }

    @Test
    public void saveUser(){
        userDao.saveUser(new User(null, "admin", "123456", "qwer@dd.com"));
    }



}
