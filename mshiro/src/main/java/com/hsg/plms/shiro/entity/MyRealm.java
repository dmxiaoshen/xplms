
package com.hsg.plms.shiro.entity;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.AllPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.hsg.plms.base.entity.ShiroUser;
import com.hsg.plms.home.entity.User;
import com.hsg.plms.home.service.UserService;

public class MyRealm extends AuthorizingRealm{
    
    @Autowired
    private UserService userService;

    /** 授权回调*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
       //principals.fromRealm(getName()).iterator().next();
        //ShiroUser u = (ShiroUser)principals.getPrimaryPrincipal();
        //String currentUsername = (String)super.getAvailablePrincipal(principals);
        ShiroUser shiroUser = (ShiroUser) principals.fromRealm(getName()).iterator().next(); // 只有一个桥梁
        User user = userService.findUserByName(shiroUser.getUsername());
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();           
            info.addObjectPermission(new AllPermission());          
            return info;
        }
        return null;
    }

    /** 登录回调*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String name = token.getPrincipal().toString();
        User user = userService.findUserByName(name);
        if(user!=null){
            Object principal = getPrincipal(user);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, user.getPassword(),new SimpleByteSource(user.getUsername()), getName());
            return info;
        }
        return null;
    }
    
    private Object getPrincipal(User user){
        ShiroUser principal = new ShiroUser();
        principal.setUserId(user.getId());
        principal.setUsername(user.getUsername());
        return principal;
    }

}
