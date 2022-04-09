package com.le.oauth2.server.model.vo;

import com.le.oauth2.server.model.po.SysRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysRolePermissionVO {

    //权限ID
    private Long permissionId;

    //权限url
    private String url;

    //权限名称
    private String permissionName;

    private List<SysRole> roles;
}
