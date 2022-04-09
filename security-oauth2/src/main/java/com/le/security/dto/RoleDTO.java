package com.le.security.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: xll
 * @Date: 2022/1/16 19:16
 */
@Data
public class RoleDTO {
    private String id;
    private String role_name;
    private String description;
    private Date create_time;
    private Date update_time;
    private String status;
}
