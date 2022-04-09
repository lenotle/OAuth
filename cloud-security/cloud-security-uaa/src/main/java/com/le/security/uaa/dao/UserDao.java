package com.le.security.uaa.dao;

import com.le.security.uaa.dto.PermissionDTO;
import com.le.security.uaa.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: xll
 * @Date: 2022/1/16 16:27
 */
@Repository
public class UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    // 根据用户名查询用户信息
    public UserDTO queryUserByUsername(String username) {
        String sql = "select * from t_user where username=?";
        List<UserDTO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserDTO.class), username);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    // 根据用户id查询权限
    public List<String> queryPermissionsByUserId(String userId) {
        String sql = "select\n" +
                "  code\n" +
                "from\n" +
                "  t_permission\n" +
                "where id in\n" +
                "  (select\n" +
                "    permission_id\n" +
                "  from\n" +
                "    t_role_permission\n" +
                "  where role_id in\n" +
                "    (select\n" +
                "      role_id\n" +
                "    from\n" +
                "      t_user_role\n" +
                "    where user_id = ?))";
        List<PermissionDTO> permissionDTOS = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PermissionDTO.class), userId);
        return permissionDTOS.stream().map(e -> e.getCode()).collect(Collectors.toList());
    }
}
