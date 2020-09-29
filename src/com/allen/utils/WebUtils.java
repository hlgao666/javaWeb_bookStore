package com.allen.utils;

import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


/**
 * @author Allen
 * @date 2020/9/19 21:26
 */
public class WebUtils {
    public static <T> T copyParamToBean(Map value, T bean){

        try {
            BeanUtils.populate(bean,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static Integer parseInt(String str, int default_value) {

        try{
            return Integer.parseInt(str);
        }catch (Exception e){
//            e.printStackTrace();
        }
        return default_value;
    }
}
