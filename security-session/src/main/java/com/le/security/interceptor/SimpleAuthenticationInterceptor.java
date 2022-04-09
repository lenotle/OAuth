package com.le.security.interceptor;

import com.le.security.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Auther: xll
 * @Date: 2022/1/14 13:34
 */
@Component
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object obj = request.getSession().getAttribute(UserDto.SESSION_KEY);
        if (obj == null) { // 未登录
            writeContent(response, "未登录");
            return false;
        }
        // 已登录
        UserDto user = (UserDto) obj;
        String uri = request.getRequestURI();
        if (user.getAuthorities().contains("p1") && uri.contains("test1")) {
            return true;
        }
        if (user.getAuthorities().contains("p2") && uri.contains("test2")) {
            return true;
        }

        writeContent(response, "没有访问资源权限");

        return false;
    }

    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println(msg);
        writer.close();
        response.reset();
    }
}
