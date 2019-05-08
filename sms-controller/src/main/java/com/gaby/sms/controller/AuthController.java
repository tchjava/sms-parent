package com.gaby.sms.controller;

import com.gaby.exception.BssException;
import com.gaby.model.DefaultResponse;
import com.gaby.sms.system.SystemUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
*@discrption:认证控制器
*@user:Gaby
*@createTime:2019-04-28 19:53
*/
@Controller
public class AuthController {

    @RequestMapping("/auth")
    @ResponseBody
    public DefaultResponse auth(HttpServletRequest request) {
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        if (null != exceptionClassName) {
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                throw new BssException("帐号或密码错误");
            }
            if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                throw new BssException("帐号或密码错误");
            }else if("success".equals(exceptionClassName)){
                return DefaultResponse.DEFAULT_RESPONSE;
            } else if ("error".equals(exceptionClassName)) {
                throw new BssException(exceptionClassName);
            } else {
                throw new BssException("登录超时,请重新登录");
            }
        }
        return DefaultResponse.DEFAULT_RESPONSE;
    }

    @RequestMapping("/index")
    @ResponseBody
    public SystemUser index() {
        System.out.println("index........");
        SystemUser principal = (SystemUser) SecurityUtils.getSubject().getPrincipal();
        if (null == principal) {
            throw new BssException("登陆超时，请重新登录");
        }
        return principal;
    }
}
