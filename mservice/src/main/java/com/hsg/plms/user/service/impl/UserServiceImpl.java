
package com.hsg.plms.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import com.hsg.plms.base.entity.Page;
import com.hsg.plms.base.entity.PageUtil;
import com.hsg.plms.base.entity.Pagination;
import com.hsg.plms.base.service.AbstractService;
import com.hsg.plms.common.enums.UserStatusEnum;
import com.hsg.plms.user.entity.User;
import com.hsg.plms.user.service.UserService;

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

    @Override
    public User findById(Long userId) {
        String hql = " from User where id=:userId and status=:status";
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("userId", userId);
        paramMap.put("status", UserStatusEnum.Work);
        return hibernateDao.findOne(hql, paramMap);
    }

    @Override
    public void deleteUserById(Long userId) {
        hibernateDao.delete(User.class, userId);        
    }

    @Override
    public Pagination<User> queryUserData(Page page) {
        String hql = " from User ";
        
        Order order = PageUtil.getOrder(page);
        if(order!=null){
            hql += "order by "+order.toString();
        }
        return hibernateDao.list(hql, null, page.getPage(), page.getRows());
    }

}
