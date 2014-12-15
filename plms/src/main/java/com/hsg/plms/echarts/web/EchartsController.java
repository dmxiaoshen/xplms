
package com.hsg.plms.echarts.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/echarts")
public class EchartsController {

    
    @RequestMapping(value="/index")
    public String echartsPage(Model model){
        return "/echarts/index";
    }
}
