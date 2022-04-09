package com.le.oauth2.server.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.le.oauth2.common.model.SecurityUser;
import com.le.oauth2.common.model.SysConstant;
import com.le.oauth2.server.dao.SysRoleRepository;
import com.le.oauth2.server.dao.SysUserRepository;
import com.le.oauth2.server.dao.SysUserRoleRepository;
import com.le.oauth2.server.model.po.SysUser;
import com.le.oauth2.server.model.po.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * UserDetailsService的实现类，从数据库加载用户的信息，比如密码、角色。。。。
 */
@Service(value = "jwtTokenUserDetailsServiceV2")
@Primary
public class JwtTokenUserDetailsServiceV2 implements UserDetailsService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;

    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserRepository.findByUsernameAndStatus(username, 1);
        if (Objects.isNull(user))
            throw new UsernameNotFoundException("用户不存在！");
        //角色
        List<SysUserRole> sysUserRoles = sysUserRoleRepository.findByUserId(user.getId());
        //该用户的所有权限（角色）
        List<String> roles=new ArrayList<>();
        for (SysUserRole userRole : sysUserRoles) {
            sysRoleRepository.findById(userRole.getRoleId()).ifPresent(o-> roles.add(SysConstant.ROLE_PREFIX+o.getCode()));
        }
        return SecurityUser.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                //将角色放入authorities中
                .authorities(AuthorityUtils.createAuthorityList(ArrayUtil.toArray(roles,String.class)))
                .build();
    }
}