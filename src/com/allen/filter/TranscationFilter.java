package com.allen.filter;

import com.allen.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Allen
 * @date 2020/9/29 14:29
 */
public class TranscationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);  //把异常抛出到Tomcat服务器，展示友好页面
        }
    }
}
