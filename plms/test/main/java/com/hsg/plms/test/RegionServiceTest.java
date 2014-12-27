package com.hsg.plms.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hsg.plms.region.entity.Region;
import com.hsg.plms.region.service.RegionService;
import com.hsg.plms.test.base.BaseTest;

public class RegionServiceTest extends BaseTest {

    @Autowired
    private RegionService regionService;

    @Test
    public void testSearch() {
        List<Region> list = regionService.findAllProvince();
        Region r = list.get(0);
        System.out.println(list.size());
        System.out.println(r.getName()+"---"+r.getCode());
    }
}
