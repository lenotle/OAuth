package com.le.security.uaa.dto;

import lombok.Data;

/**
 * @Auther: xll
 * @Date: 2022/1/16 19:20
 */
@Data
public class RolePermission {
    private String role_id;
    private String permission_id;
}
