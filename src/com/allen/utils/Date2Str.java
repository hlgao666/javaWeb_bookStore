package com.allen.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Allen
 * @date 2020/9/28 20:24
 */
public class Date2Str {
    public static String getTimeNow(){
        Timestamp ts = new Timestamp(new Date().getTime());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(ts);
        return time;    //返回当前时间
    }
}
