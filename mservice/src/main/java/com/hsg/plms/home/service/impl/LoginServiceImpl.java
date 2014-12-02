
package com.hsg.plms.home.service.impl;

import org.springframework.stereotype.Service;

import com.hsg.plms.base.service.AbstractService;
import com.hsg.plms.home.entity.User;
import com.hsg.plms.home.service.LoginService;

@Service
public class LoginServiceImpl extends AbstractService implements LoginService{

    @Override
    public void saveUser(User u) {
        hibernateDao.save(u);
        
    }

}
