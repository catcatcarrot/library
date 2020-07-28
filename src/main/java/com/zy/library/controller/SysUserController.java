package com.zy.library.controller;

import com.zy.library.entity.Msg;
import com.zy.library.entity.SysRole;
import com.zy.library.entity.SysUser;
import com.zy.library.entity.SysUserMsg;
import com.zy.library.repository.SysUserRepository;
import com.zy.library.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRepository sysUserRepository;

    //测试
    @ResponseBody
    @RequestMapping("/findByUserName")
    public SysUser findByUserName(String userName){
        SysUser user = sysUserRepository.findByUserNumber(userName);
        Set<SysRole> roles = user.getRoles();
        roles.forEach(r-> System.out.println(r.getRoleName()));
        return user;
    }

    @ResponseBody
    @RequestMapping("/verifyCode")
    public Msg verifyCode(String code, HttpSession session){
        String verifyCode = (String) session.getAttribute("verifyCode");
        if(!verifyCode.toUpperCase().equals(code.toUpperCase())) {
            return Msg.fail();
        }else {
            return Msg.success();
        }
    }


    @RequestMapping("/login")
    public String login(){
        return "forward:/login/login.html";
    }

    @ResponseBody
    @RequestMapping("/getUser")
    public SysUserMsg getUser(HttpSession session){
        return (SysUserMsg)session.getAttribute("userMsg");
    }


}
