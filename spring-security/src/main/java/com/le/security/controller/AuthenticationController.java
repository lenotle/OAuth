package com.le.security.controller;

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
        return "login success !";
    }

    @GetMapping("/r/r1")
    public String test1() {
        return "r1";
    }

    @GetMapping("/r/r2")
    public String test2() {
        return "r2";
    }
}
