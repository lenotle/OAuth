package com.le.oauth2.server.dao;

import com.le.oauth2.server.model.po.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleRepository  extends JpaRepository<SysRole,Long> {
}
