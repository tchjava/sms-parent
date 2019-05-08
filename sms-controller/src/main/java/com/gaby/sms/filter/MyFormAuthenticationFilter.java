package com.gaby.sms.filter;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
    private static final Logger log = LoggerFactory.getLogger(MyFormAuthenticationFilter.class);
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        if (!super.onAccessDenied(request, response)) {
//            request.setAttribute("shiroLoginFailure","success");
//        }
//        return true;
        if (isLoginSubmission(request, response)) {
            if (log.isTraceEnabled()) {
                log.trace("Login submission detected.  Attempting to execute login.");
            }
            if(!executeLogin(request, response)){
                request.setAttribute("shiroLoginFailure","success");
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Login page view.");
            }
            //allow them to see the login page ;)
            request.setAttribute("shiroLoginFailure","error");
        }
        return true;
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }
}
