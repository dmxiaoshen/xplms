
package com.hsg.plms.base.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsg.plms.base.dao.HibernateDao;
import com.hsg.plms.base.entity.BaseEntity;

@Repository
public class HibernateDaoImpl implements HibernateDao{

    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(BaseEntity entity) {
        getSession().save(entity);      
    }
    
}
