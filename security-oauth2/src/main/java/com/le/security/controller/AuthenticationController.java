package com.le.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: xll
 * @Date: 2022/1/14 14:59
 */
@RestController
public class AuthenticationController {

    @RequestMapping("/login-success")
    public String success() {
        return getUsername() + " login success !";
    }

    @PreAuthorize("hasAuthority('p1')") // p1权限
    @GetMapping("/r/r1")
    public String test1() {
        return getUsername() + " r1";
    }

    @PreAuthorize("hasAuthority('p3')")
    @GetMapping("/r/r2")
    public String test2() {
        return getUsername() + " r2";
    }

    /**
     * 获取当前用户登录的信息
     * @return
     */
    private String getUsername() {
        String username = "invalid authentication";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new RuntimeException("getUsername: authentication is null");
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            username = userDetails.getUsername();
        }
        return username;
    }
}
