
package com.hsg.plms.home.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/login")
public class LoginController {

    @RequestMapping(value="/index")
    public String loginPage(){
        return "/index";
    }
}
