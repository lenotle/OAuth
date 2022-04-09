package com.le.security.controller;

import com.alibaba.fastjson.JSONWriter;
import com.le.security.dto.AuthenticationRequest;
import com.le.security.dto.UserDto;
import com.le.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Auther: xll
 * @Date: 2022/1/14 11:10
 */
@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationService service;

    @PostMapping(value = "/login")
    public String login(AuthenticationRequest request, HttpSession httpSession) {
        UserDto authentication = service.authentication(request);
        // login success
        httpSession.setAttribute(UserDto.SESSION_KEY, authentication);
        return authentication.getFullName();
    }

    @GetMapping(value = "/test1", produces = {"text/plain;charset=UTF-8"})
    public String test(HttpSession httpSession) {
        Object attribute = httpSession.getAttribute(UserDto.SESSION_KEY);
        if (attribute != null) {
            return "访问test1";
        }
        return "无session";
    }

    @GetMapping(value = "/test2", produces = {"text/plain;charset=UTF-8"})
    public String test2(HttpSession httpSession) {
        Object attribute = httpSession.getAttribute(UserDto.SESSION_KEY);
        if (attribute != null) {
            return "访问test2";
        }
        return "无session";
    }

    @GetMapping(value = "/logout", produces = {"text/plain;charset=UTF-8"})
    public String logout(HttpSession session) {
        session.removeAttribute(UserDto.SESSION_KEY);
        return "退出登录成功";
    }
}
