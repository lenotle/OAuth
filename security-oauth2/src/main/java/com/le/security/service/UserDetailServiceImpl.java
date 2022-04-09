package com.le.security.service;

import com.le.security.dao.UserDao;
import com.le.security.dto.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: xll
 * @Date: 2022/1/16 15:51
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserDao userDao;

    /**
     * 实现从数据库查询
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userDao.queryUserByUsername(username);
        if (userDTO == null || userDTO.getId() == null || StringUtils.isBlank(userDTO.getPassword())) {
            return null;
        }
        // 根据用户id查询权限
        List<String> permissions = userDao.queryPermissionsByUserId(String.valueOf(userDTO.getId()));

        UserDetails userDetails = User
                .withUsername(userDTO.getUsername()) // username
                .password(userDTO.getPassword())     // password
                .authorities(permissions.toArray(new String[permissions.size()]))  // user access permission
                .build();
        return userDetails;
    }
}
