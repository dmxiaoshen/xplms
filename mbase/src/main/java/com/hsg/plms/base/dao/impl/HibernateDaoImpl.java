package com.hsg.plms.base.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.hsg.plms.base.dao.HibernateDao;

@Repository
public class HibernateDaoImpl implements HibernateDao {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(Object object) {
        Assert.notNull(object, "add object can not be null");
        getSession().save(object);
    }

    @Override
    public void update(Object object) {
        Assert.notNull(object, "update object can not be null");
        getSession().saveOrUpdate(object);
    }

    @Override
    public void delete(Object object) {
        Assert.notNull(object, "delete object can not be null");
        getSession().delete(object);

    }

    @Override
    public void delete(Class<?> clzss, Long id) {
        Assert.notNull(id, "delete id can not be null");
        getSession().delete((load(clzss, id)));
        
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T load(Class<T> clzss, Long id) {
        Assert.notNull(id, "load id can not be null");       
        return (T) getSession().load(clzss, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Class<T> clzss, Long id) {
        Assert.notNull(id, "get id can not be null");      
        return (T) getSession().get(clzss, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> findList(String hql, Map<String, Object> paramMap) {
        Query query = getSession().createQuery(hql);
        setQuery(query,paramMap);
        return query.list();
    }
    
    private void setQuery(Query query, final Map<String, Object> paramMap) {
        if (paramMap == null || paramMap.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            Object v = entry.getValue();
            if (v instanceof Object[]) {
                query.setParameterList(entry.getKey(), (Object[]) v);
            } else if (v instanceof Collection) {
                query.setParameterList(entry.getKey(), (Collection<?>) v);
            } else {
                query.setParameter(entry.getKey(), v);
            }
        }
    }

    @Override
    public <T> T findOne(String hql, Map<String, Object> paramMap) {
        List<T> list = findList(hql, paramMap);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }



}
