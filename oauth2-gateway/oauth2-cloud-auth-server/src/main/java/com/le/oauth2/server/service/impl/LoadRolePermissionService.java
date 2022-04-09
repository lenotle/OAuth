package com.le.oauth2.server.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.le.oauth2.common.model.SysConstant;
import com.le.oauth2.server.model.po.SysRole;
import com.le.oauth2.server.model.vo.SysRolePermissionVO;
import com.le.oauth2.server.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 在项目启动时从数据库中将url->角色对应关系加载到Redis中
 */
@Service
public class LoadRolePermissionService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private PermissionService permissionService;

    @PostConstruct
    public void init() {
        // 获取所有的权限
        List<SysRolePermissionVO> list = permissionService.listRolePermission();
        list.parallelStream().peek(k -> {
            List<String> roles = new ArrayList<>();
            if (CollectionUtil.isNotEmpty(k.getRoles())) {
                for (SysRole role : k.getRoles()) {
                    roles.add(SysConstant.ROLE_PREFIX + role.getCode());
                }
            }
            //放入Redis中
            redisTemplate.opsForHash().put(SysConstant.OAUTH_URLS, k.getUrl(), JSON.toJSONString(roles));
        }).collect(Collectors.toList());
    }

}
