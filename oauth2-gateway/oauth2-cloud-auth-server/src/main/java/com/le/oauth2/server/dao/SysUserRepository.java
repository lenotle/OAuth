package com.le.oauth2.server.dao;


import com.le.oauth2.server.model.po.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepository extends JpaRepository<SysUser,Long> {
    SysUser findByUsernameAndStatus(String username,Integer status);
}
