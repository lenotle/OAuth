package com.le.security.order.common;


import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Auther: xll
 * @Date: 2022/1/19 17:02
 */
public class UserUtil {
    /**
     * 获取用户信息
     * @return username
     */
    public static String getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}
