package com.allen.test;


import com.allen.pojo.User;
import com.allen.service.UserService;
import com.allen.service.impl.UserServiceImpl;
import org.junit.Test;


/**
 * @author Allen
 * @date 2020/9/16 10:14
 */
public class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void registUser(){
        userService.registUser(new User(null,"zxc","666666","666666@qq.com"));
    }

    @Test
    public void login(){
        System.out.println(userService.login(new User(null,"zxc","666666","666666@qq.com")));
    }

    @Test
    public void existsUsername(){
        if(userService.existsUsername("111")){
            System.out.println("user has existed");
        }else{
            System.out.println("username is able to use");
        }
    }
}
