package com.hsg.plms.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.hsg.plms.common.enums.UserStatusEnum;
import com.hsg.plms.common.utils.ShiroUtil;
import com.hsg.plms.test.base.BaseTest;
import com.hsg.plms.user.entity.User;
import com.hsg.plms.user.service.UserService;

public class UserServiceTest extends BaseTest {
    @Autowired
    private UserService userService;

    @Test
    @Rollback(value = false)
    public void testInsert() {
        User u = new User();
        u.setUsername("Lucy");
        u.setPassword(ShiroUtil.encryptPassword("1", u.getUsername()));
        u.setStatus(UserStatusEnum.Work);
        Date now = new Date();
        u.setCreateTime(now);
        u.setUpdateTime(now);
        userService.saveUser(u);
    }

    @Test
    @Rollback(value = false)
    public void testDel() {
        userService.deleteUserById(5L);
    }

}
