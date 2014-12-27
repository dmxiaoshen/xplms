package com.hsg.plms.shiro.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.hsg.plms.common.constants.AppConstants;
import com.hsg.plms.shiro.entity.CaptchaUsernamePasswordToken;
import com.hsg.plms.shiro.exception.IncorrectCaptchaException;

public class MyAuthenticatingFilter extends FormAuthenticationFilter {

    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    public String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    @Override
    protected CaptchaUsernamePasswordToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        return new CaptchaUsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha);
    }

    // 验证码校验
    protected void doCaptchaValidate(HttpServletRequest request, CaptchaUsernamePasswordToken token) {
        // session中的图形码字符串
        String captcha = (String) request.getSession().getAttribute(
                AppConstants.CAPTCHA_CODE);
        // 比对
        if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
            throw new IncorrectCaptchaException("验证码错误！");
        }
    }
    
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        CaptchaUsernamePasswordToken token = createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                    "must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        }
        try {
            doCaptchaValidate((HttpServletRequest)request, token);
            Subject subject = getSubject(request, response);
            subject.login(token);
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }
    }

    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        /** 放入异常的完整信息，控制层controller需要用到 */
        request.setAttribute(getFailureKeyAttribute(), ae);
    }
    
    @Override
    protected void saveRequestAndRedirectToLogin(ServletRequest req, ServletResponse rep) throws IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;
        if (request.getHeader("x-requested-with") != null) {
            response.setHeader("sesssionIsTimeout", "true");//未登录时ajax请求处理
        } else {
            super.saveRequestAndRedirectToLogin(request, response);
        }
    }

}
