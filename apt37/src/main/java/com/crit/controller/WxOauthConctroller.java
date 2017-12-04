package com.crit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WxOauthConctroller {

    Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("mp/{org}.do")
    @ResponseBody
    public Object weiXinTokenValidata(@PathVariable("org") String org, HttpServletRequest request, HttpServletResponse response) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        log.info("接入接口校验:{},{},{},{},{}", org, signature, timestamp, nonce, echostr);
        if (StringUtils.isNotBlank(signature)) {
            log.info("签名串是：{}", signature);
        }
        return StringUtils.defaultIfBlank(echostr, "为获取到参数");
    }

    @RequestMapping("mp/oauth")
    @ResponseBody
    public Object getUserOpenid(@PathVariable("org") String org, HttpServletRequest request, HttpServletResponse response) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        log.info("接入接口校验:{},{},{},{},{}", org, signature, timestamp, nonce, echostr);
        if (StringUtils.isNotBlank(signature)) {
            log.info("签名串是：{}", signature);
        }
        return StringUtils.defaultIfBlank(echostr, "为获取到参数");
    }

}
