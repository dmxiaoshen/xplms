
package com.hsg.plms.home.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hsg.plms.home.entity.User;
import com.hsg.plms.home.service.LoginService;

@Controller
@RequestMapping(value="/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value="/index")
    public String loginPage(){
        User u = new User();
        u.setUsername("testc");
        u.setPassword("123");
        Date now = new Date();
        u.setCreateTime(now);
        u.setUpdateTime(now);
        loginService.saveUser(u);
        return "/index";
    }
}
