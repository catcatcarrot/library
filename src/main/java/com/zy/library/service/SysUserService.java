package com.zy.library.service;

import com.zy.library.entity.SysUser;

public interface SysUserService {

    public SysUser findByUserName(String userName);

    public SysUser findByUserNumber(String number);

}
