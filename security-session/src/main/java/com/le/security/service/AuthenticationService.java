package com.le.security.service;

import com.le.security.dto.AuthenticationRequest;
import com.le.security.dto.UserDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: xll
 * @Date: 2022/1/14 10:58
 */
@Service
public class AuthenticationService {

    private Map<String, UserDto> map = new HashMap<>();

    {
        Set<String> authorities1 = new HashSet<>();
        authorities1.add("p1");

        Set<String> authorities2 = new HashSet<>();
        authorities2.add("p2");

        map.put("zhangsan", new UserDto(1L, "zhangsan", "123", "zhangsan", authorities1));
        map.put("lisi", new UserDto(2L, "lisi", "123", "lisi", authorities2));
    }

    /**
     * 用户认证接口
     * @param authenticationRequest 用户请求信息
     * @return 认证后信息
     */
    public UserDto authentication(AuthenticationRequest authenticationRequest) {
        if (authenticationRequest == null || StringUtils.isBlank(authenticationRequest.getUsername()) || StringUtils.isBlank(authenticationRequest.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }

        UserDto userDto = getUserDtoByUserName(authenticationRequest.getUsername());
        if (userDto == null) {
            throw new RuntimeException("用户名不正确，请输入正确用户名");
        }
        if (StringUtils.isBlank(userDto.getPassword()) ||
                !StringUtils.equals(authenticationRequest.getPassword(), userDto.getPassword())) {
            throw new RuntimeException("密码错误");
        }
        return userDto;
    }

    private UserDto getUserDtoByUserName(String username) {
        return map.get(username);
    }
}
