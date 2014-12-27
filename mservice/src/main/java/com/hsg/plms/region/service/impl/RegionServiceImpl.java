
package com.hsg.plms.region.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import com.hsg.plms.base.entity.Page;
import com.hsg.plms.base.entity.PageUtil;
import com.hsg.plms.base.entity.Pagination;
import com.hsg.plms.base.service.AbstractService;
import com.hsg.plms.common.constants.AppConstants;
import com.hsg.plms.region.entity.Region;
import com.hsg.plms.region.service.RegionService;

@Service
public class RegionServiceImpl extends AbstractService implements RegionService{

    @Override
    public List<Region> findAllProvince() {
        String hql = " from Region where region.id is null";      
        return hibernateDao.findList(hql, null);
    }

    @Override
    public List<Region> findCityByProId(Long province) {
        if(province!=null){
            String hql = " from Region where region.id=:proId";
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("proId", province);
            return hibernateDao.findList(hql, paramMap);
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> queryAutoCity(String name) {
        String hql = " from Region where name like :name";
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("name", "%"+name+"%");
        List<Region> list = hibernateDao.findList(hql, paramMap, 0, AppConstants.MAX_AUTO_COMPLETE);
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        Map<String,Object> temp = null;
        for(Region region:list){
            temp = new HashMap<String,Object>();
            temp.put("label", region.getName());
            temp.put("value", region.getId());
            result.add(temp);
        }
        return result;
    }

    @Override
    public Pagination<Region> queryProvince(Page page) {
        String hql = " from Region where region.id is null";
        Order order = PageUtil.getOrder(page);
        if(order!=null){
            hql+=" order by "+order.toString();
        }
        return hibernateDao.list(hql, null, PageUtil.getPage(page), PageUtil.getPageSize(page));
    }

    @Override
    public Pagination<Region> queryProvinceChild(Region region, Page page) {
        String hql = " from Region where region.id =:regionId";
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("regionId", region.getId());
        Order order = PageUtil.getOrder(page);
        if(order!=null){
            hql+=" order by "+order.toString();
        }
        return hibernateDao.list(hql, paramMap, PageUtil.getPage(page), PageUtil.getPageSize(page));
    }
    

}
