
package com.hsg.plms.user.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsg.plms.base.entity.Page;
import com.hsg.plms.common.enums.UserStatusEnum;
import com.hsg.plms.common.utils.ShiroUtil;
import com.hsg.plms.user.entity.User;
import com.hsg.plms.user.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value="/add")
    public String userAddPage(){
        
        return "/user/add";
    }
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public String addUser(User user){
        
        try{Date now = new Date();
        user.setPassword(ShiroUtil.encryptPassword(user.getPassword(), user.getUsername()));
        user.setStatus(UserStatusEnum.Work);
        user.setCreateTime(now);
        user.setUpdateTime(now);
        userService.saveUser(user);
        return "redirect:/home/index";
        }catch(Exception e){
            return "redirect:/user/add";
        }
    }
    
    @RequestMapping(value="/checkUsername")
    @ResponseBody
    public boolean checkUsername(String username){
        User u = userService.findUserByName(username);
        return u==null;
    }
    
    @RequestMapping(value="/update/{userId}")
    public String updateUserPage(ModelMap model,@PathVariable("userId") Long userId){
        User u = userService.findById(userId);
        model.put("user", u);
        return "/user/update";
        
    }
    
    @RequestMapping(value="/update")
    @ResponseBody
    public String updateUser(User user){
        
        try{
            User u = userService.findById(user.getId());
            u.setUsername(user.getUsername());
            u.setUpdateTime(new Date());
            userService.saveUser(u);
            return "success";
        }catch(Exception e){
            return "fail";
        }
    }
    

    @RequestMapping(value="/listPage")
    public String userList(Model model){
        return "/user/list";
    }
    
    @RequestMapping(value="/list")
    @ResponseBody
    public Object userListData(Page page){
        return userService.queryUserData(page);
    }
}
