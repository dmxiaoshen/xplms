
package com.hsg.plms.home.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hsg.plms.base.entity.ShiroUser;
import com.hsg.plms.common.utils.ShiroUtil;

@Controller
@RequestMapping(value="/home")
public class HomeController {

    @RequestMapping(value="/index")
    public String home(Model model){
        ShiroUser x = ShiroUtil.getShiroUser();
        model.addAttribute("shiroUser", x);
        return "/home/index";
    }
}
