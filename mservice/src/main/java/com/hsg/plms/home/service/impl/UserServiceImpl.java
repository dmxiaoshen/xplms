
package com.hsg.plms.home.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hsg.plms.base.service.AbstractService;
import com.hsg.plms.common.enums.UserStatusEnum;
import com.hsg.plms.home.entity.User;
import com.hsg.plms.home.service.UserService;

@Service
public class UserServiceImpl extends AbstractService implements UserService{

    @Override
    public void saveUser(User u) {
        hibernateDao.add(u);    
    }

    @Override
    public User findUserByName(String username) {
        String hql = " from User where username=:username and status=:status";
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("username", username);
        paramMap.put("status", UserStatusEnum.Work);
        return hibernateDao.findOne(hql, paramMap);
    }

}
