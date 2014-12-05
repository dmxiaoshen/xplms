package com.hsg.plms.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;

import com.hsg.plms.base.entity.ShiroUser;

public class ShiroUtil {
    public static ShiroUser getShiroUser() {
        return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    }

    public static String encryptPassword(String password, String username) {
        return new Sha256Hash(password, username).toBase64();
    }
}
