package com.hsg.plms.base.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hsg.plms.base.dao.HibernateDao;

public abstract class AbstractService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected HibernateDao hibernateDao;

}
