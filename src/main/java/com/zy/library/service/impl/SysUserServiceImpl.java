package com.zy.library.service.impl;

import com.zy.library.entity.SysUser;
import com.zy.library.repository.SysUserRepository;
import com.zy.library.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;


    @Override
    public SysUser findByUserName(String userName) {
        return sysUserRepository.findByUserName(userName);
    }

    @Override
    @Transactional
    public SysUser findByUserNumber(String number) {
        return sysUserRepository.findByUserNumber(number);
    }
}
