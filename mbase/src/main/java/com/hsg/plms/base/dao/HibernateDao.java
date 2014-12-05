
package com.hsg.plms.base.dao;

import java.util.List;
import java.util.Map;


public interface HibernateDao {
    
    void add(Object object);
    
    void update(Object object);
    
    void delete(Object object);
    
    void delete(Class<?> clzss,Long id);
    
    <T> T load(Class<T> clzss,Long id);
    
    <T> T get(Class<T> clzss,Long id);
    
    <T> List<T> findList(String hql,Map<String,Object> paramMap);
    
    <T> T findOne(String hql,Map<String,Object> paramMap);
}
