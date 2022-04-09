package com.le.security.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: xll
 * @Date: 2022/1/14 10:59
 */
@Data
public class AuthenticationRequest implements Serializable {
    private String username;
    private String password;
}
