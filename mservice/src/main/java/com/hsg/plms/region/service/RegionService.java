
package com.hsg.plms.region.service;

import java.util.List;
import java.util.Map;

import com.hsg.plms.base.entity.Page;
import com.hsg.plms.base.entity.Pagination;
import com.hsg.plms.region.entity.Region;

public interface RegionService {

    List<Region> findAllProvince();

    List<Region> findCityByProId(Long province);

    List<Map<String,Object>> queryAutoCity(String name);

    Pagination<Region> queryProvince(Page page);

    Pagination<Region> queryProvinceChild(Region region, Page page);

}
