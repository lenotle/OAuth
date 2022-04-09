package com.le.security.uaa.dto;

import lombok.Data;

/**
 * @Auther: xll
 * @Date: 2022/1/16 16:25
 */
@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;

}
