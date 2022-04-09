package com.le.security.order.controller;

import com.le.security.order.common.UserUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: xll
 * @Date: 2022/1/18 21:55
 */
@RestController
public class AuthorizationController {

    @GetMapping(value = "/r/r1")
    @PreAuthorize("hasAuthority('p1')")
    public String r1() {
        return UserUtil.getPrincipal() +  "access r1 resource";
    }

    @GetMapping(value = "/test", produces = {"text/plain"})
    public String r2() {
        System.out.println("access test11");
        return  "access test";
    }
}
