package com.zy.library.repository;

import com.zy.library.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser,Long> {

    public SysUser findByUserName(String userName);

    public SysUser findByUserNumber(String number);

}
