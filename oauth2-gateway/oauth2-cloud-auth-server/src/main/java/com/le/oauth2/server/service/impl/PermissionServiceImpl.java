package com.le.oauth2.server.service.impl;

import com.le.oauth2.server.dao.SysPermissionRepository;
import com.le.oauth2.server.dao.SysRolePermissionRepository;
import com.le.oauth2.server.dao.SysRoleRepository;
import com.le.oauth2.server.model.po.SysPermission;
import com.le.oauth2.server.model.po.SysRole;
import com.le.oauth2.server.model.po.SysRolePermission;
import com.le.oauth2.server.model.vo.SysRolePermissionVO;
import com.le.oauth2.server.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Autowired
    private SysPermissionRepository sysPermissionRepository;

    @Autowired
    private SysRolePermissionRepository sysRolePermissionRepository;

    @Override
    public List<SysRolePermissionVO> listRolePermission() {
        List<SysRolePermissionVO> list = new ArrayList<>();
        List<SysPermission> permissions = sysPermissionRepository.findAll();

        for (SysPermission permission : permissions) {
            List<SysRolePermission> rolePermissions = sysRolePermissionRepository.findByPermissionId(permission.getId());
            List<SysRole> roles = rolePermissions.stream()
                    .map(k -> sysRoleRepository.findById(k.getRoleId()).get())
                    .collect(Collectors.toList()
            );
            SysRolePermissionVO vo = SysRolePermissionVO.builder()
                    .permissionId(permission.getId())
                    .url(permission.getUrl())
                    .permissionName(permission.getName())
                    .roles(roles)
                    .build();
            list.add(vo);
        }
        return list;
    }
}
