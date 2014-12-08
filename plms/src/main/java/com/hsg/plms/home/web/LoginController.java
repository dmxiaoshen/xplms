package com.hsg.plms.home.web;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsg.plms.common.constants.AppConstants;
import com.hsg.plms.common.utils.CaptchaUtil;
import com.hsg.plms.home.entity.User;
import com.hsg.plms.shiro.exception.IncorrectCaptchaException;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @RequestMapping(value = "/index")
    public String loginPage(Model model) {
        Subject sub = SecurityUtils.getSubject();
        if (sub != null) {
            sub.logout();
        }
        return "/login/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String login(User user,HttpServletRequest request,Model model,HttpSession session){
        model.addAttribute("user", user); 
//        String capt = request.getParameter("captcha");
//        String captcha = (String)session.getAttribute(AppConstants.CAPTCHA_CODE);
//        if(StringUtils.isNotBlank(captcha) && !captcha.equalsIgnoreCase(capt)){
//            model.addAttribute("errorCode", "2");  
//            model.addAttribute("error", "* 验证码错误");          
//            return loginPage(model);
//        }
        Object obj = request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        AuthenticationException authExp = (AuthenticationException) obj;
        if (authExp != null) {
            if(authExp instanceof IncorrectCaptchaException){
                model.addAttribute("errorCode", "2");  
                model.addAttribute("error", "* 验证码错误");
            }
            else if(authExp instanceof UnknownAccountException || authExp instanceof IncorrectCredentialsException){
                model.addAttribute("errorCode", "1");  
                model.addAttribute("error", "* 用户名或密码错误");
            }     
            
            return loginPage(model);
        }
        
//        try{
//            
//       
//        Subject subject = SecurityUtils.getSubject();
//        AuthenticationToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
//        
//        subject.login(token);
//        }catch(Exception e){
//            model.addAttribute("error", "* 用户名或密码错误");
//            return loginPage(model);
//        }
        
        return "redirect:/";
    }

    /**
     * 退出
     * 
     * @return 主页
     */
    @RequestMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/login/index";
    }

    @RequestMapping(value = "/getCaptcha")
    public void getCaptcha(OutputStream out, HttpSession session) {
        String code = RandomStringUtils.random(4, true, true);
        try {
            CaptchaUtil.writeImage(code, out);
            session.setAttribute(AppConstants.CAPTCHA_CODE, code);
        } catch (IOException e) {
            // 网络异常
            session.removeAttribute(AppConstants.CAPTCHA_CODE);
        }

    }

    // @RequestMapping(value="/checkCaptcha")
    // @ResponseBody
    // public boolean checkCaptcha(String captcha,HttpSession session){
    //
    // String cap = (String)session.getAttribute(AppConstants.CAPTCHA_CODE);
    // return !cap.equalsIgnoreCase(captcha);
    // }
}
