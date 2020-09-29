package com.allen.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Allen
 * @date 2020/9/16 9:38
 */
public class JdbcUtils {


    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();
    static {

        try {
            Properties properties = new Properties();
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(inputStream);
            //dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){

        Connection conn = conns.get();
        if(conn==null){
            try {
                conn = dataSource.getConnection();
                conns.set(conn);
                conn.setAutoCommit(false);  //设置为手动管理事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    //提交事务
    public static void commitAndClose(){
        Connection conn = conns.get();
        if(conn!=null){
            try {
                conn.commit();  //提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //一定要remove()操作，因为Tomcat底层使用了线程池技术,避免内存泄漏
        conns.remove();
    }

    //回滚事务
    public static void rollbackAndClose(){
        Connection conn = conns.get();
        if(conn!=null){
            try {
                conn.rollback();  //提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        //一定要remove()操作，因为Tomcat底层使用了线程池技术，避免内存泄漏
        conns.remove();
    }
    public static void closeConnection(Connection conn){

        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
