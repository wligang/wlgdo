package com.wlgdo.common.core;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 鉴权拦截器
 * 
 * @author wlg
 *
 */
public class AuthInterceptor implements HandlerInterceptor {

    static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("***********校验权限**********");
        if (com.wlgdo.common.utils.PropertiesUtil.prop("app.loadmode").equals("ON")) {// 开启验证
            if (StringUtils.isNotBlank((CharSequence) request.getSession().getAttribute("uid"))) {
                log.info("鉴权成功{}", request.getRequestURI());
            } else {
                log.info("无权限操作该资源：{}", request.getRequestURI());
                @SuppressWarnings("deprecation")
                String redire = "redire=" + URLEncoder.encode(request.getRequestURI());
                request.getSession().setAttribute("redire", redire);
                request.getRequestDispatcher("/auth/tologin.do?" + redire).forward(request, response);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        // TODO 自动生成的方法存根

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO 自动生成的方法存根

    }
}
