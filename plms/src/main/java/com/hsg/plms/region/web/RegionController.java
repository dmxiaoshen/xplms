
package com.hsg.plms.region.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsg.plms.base.entity.Page;
import com.hsg.plms.region.entity.Region;
import com.hsg.plms.region.service.RegionService;

@Controller
@RequestMapping(value="/region")
public class RegionController {
    
    @Autowired
    private RegionService regionService;
    
    @RequestMapping(value="/index")
    public String regionPage(ModelMap model){
        model.put("proList", regionService.findAllProvince());
        return "/region/index";
    }
    
    @RequestMapping(value="/queryCity")
    @ResponseBody
    public Object findCityByProId(Long province){
        return regionService.findCityByProId(province);
    }
    
    @RequestMapping(value="/query/autoCity")
    @ResponseBody
    public Object queryAutoCity(String name){
        return regionService.queryAutoCity(name);
    }

    @RequestMapping(value="/province")
    @ResponseBody
    public Object queryProvince(Page page){
        return regionService.queryProvince(page);
    }
    
    @RequestMapping(value="/province/child")
    @ResponseBody
    public Object queryProvinceChild(Region region,Page page){
        return regionService.queryProvinceChild(region,page);
    }
}
