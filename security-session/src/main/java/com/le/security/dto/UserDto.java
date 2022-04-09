package com.le.security.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @Auther: xll
 * @Date: 2022/1/14 10:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JSONType(ignores = {"password"})
public class UserDto {
    public static final String SESSION_KEY = "_user";
    @JSONField(ordinal = 1)
    private Long id;

    @JSONField(ordinal = 2)
    private String username;

    @JSONField(name = "PASSWORD")
    private String password;

    @JSONField(ordinal = 3)
    private String fullName;

    @JSONField(ordinal = 4)
    private Set<String> authorities; // 资源权限

    public static void main(String[] args) {
        UserDto userDto = new UserDto(1L, "zhangsan", "123", "zhangsan", null);
        String s = JSON.toJSONString(userDto);
        System.out.println(s);
    }
}
