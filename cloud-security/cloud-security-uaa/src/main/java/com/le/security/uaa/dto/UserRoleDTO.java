package com.le.security.uaa.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: xll
 * @Date: 2022/1/16 19:19
 */
@Data
public class UserRoleDTO {
    private String user_id;
    private String role_id;
    private Date create_time;
    private String creator;
}
