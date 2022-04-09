package com.le.oauth2.gateway.service.impl;

import com.google.common.collect.Lists;
import com.le.oauth2.common.model.SysConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 用于初始化uir的权限到redis中
 * TODO 实际开发中需要自己维护，此处只是为了演示方便
 */
//@Service
@Deprecated
public class InitService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostConstruct
    public void init(){
        redisTemplate.opsForHash().put(SysConstant.OAUTH_URLS,"/order/login/info", Lists.newArrayList("ROLE_admin","ROLE_user"));
        redisTemplate.opsForHash().put(SysConstant.OAUTH_URLS,"/order/login/admin", Lists.newArrayList("ROLE_admin"));
        redisTemplate.opsForHash().put(SysConstant.OAUTH_URLS,"/order/info", Lists.newArrayList("ROLE_admin","ROLE_user"));
        redisTemplate.opsForHash().put(SysConstant.OAUTH_URLS,"/order/listByUserId", Lists.newArrayList("ROLE_admin","ROLE_user"));
        redisTemplate.opsForHash().put(SysConstant.OAUTH_URLS,"/oauth/logout", Lists.newArrayList("ROLE_admin","ROLE_user"));
    }

}
