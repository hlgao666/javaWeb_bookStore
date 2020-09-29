package com.allen.test;

import com.allen.utils.JdbcUtils;
import org.junit.Test;


import java.sql.Connection;

/**
 * @author Allen
 * @date 2020/9/16 9:42
 */
public class JdbcUtilsTest {

    @Test
    public void TestJdbcUtils(){

        Connection conn = JdbcUtils.getConnection();
        System.out.println(JdbcUtils.getConnection());
        JdbcUtils.closeConnection(conn);
    }
}
