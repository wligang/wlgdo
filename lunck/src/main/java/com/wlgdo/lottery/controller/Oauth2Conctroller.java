package com.wlgdo.lottery.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wlgdo.common.utils.HttpClientUtil;
import com.wlgdo.lottery.domain.ActorUser;
import com.wlgdo.lottery.service.ActorService;

import net.sf.json.JSONObject;

@Controller
public class Oauth2Conctroller {

    Logger log = LoggerFactory.getLogger(getClass());

    /** 授权api */
    String AUTHORIZE_API = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI"
            + "&response_type=code&scope=SCOPE&state=STATE#wechat_redirect ";
    /** 获取网页accesstoken api */
    String ACCESS_TOKEN_PAI = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE"
            + "&grant_type=authorization_code";

    /** 获取用户基本信息api */
    String USER_BASE_INFO_API = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    /** 本应用域名 */
    String APP_DOMAIN = "https://www.wlgdo.com/lunck/";

    @Autowired
    private ActorService actorService;

    @RequestMapping("mp/{org}")
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
        return StringUtils.defaultIfBlank(echostr, "未获取到参数");
    }

    @RequestMapping("mp/oauth/{org}")
    @ResponseBody
    public void mpOauth2(@PathVariable("org") String org, HttpServletRequest request, HttpServletResponse response) {
        log.info("授权页面正常运行");
        String auth2Api = AUTHORIZE_API.replace("APPID", "wx3cb81c3c95c1a755");
        auth2Api = auth2Api.replace("REDIRECT_URI", URLEncoder.encode(APP_DOMAIN + "mp/oauth/code"));
        auth2Api = auth2Api.replace("SCOPE", "snsapi_userinfo").replace("STATE", org);
        try {
            log.info("授权页面正常运行：{}", auth2Api);
            response.sendRedirect(auth2Api);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("授权页面出异常了，没有正常运行");
    }

    @RequestMapping("mp/oauth/code")
    public Object mpOauthCode(Model model, HttpServletRequest request, HttpServletResponse response) {
        log.info("开始获取code：{}");
        String code = request.getParameter("code");
        String orgId = request.getParameter("state");
        log.info("获取code值：{}", code);
        if (StringUtils.isBlank(code)) {
            model.addAttribute(code, code);
            model.addAttribute("msg", "获取授权失败");
            return "mp/oauth";
        }
        String accessTokenApi = ACCESS_TOKEN_PAI.replace("APPID", "wx3cb81c3c95c1a755");
        accessTokenApi = accessTokenApi.replace("SECRET", "600bd6c8edb8ca5340e409910845ca5c").replace("CODE", code);
        String httpOrgCreateTest = accessTokenApi;
        Map<String, String> createMap = new HashMap<String, String>();
        log.info("获取accesstoken：{}", httpOrgCreateTest);
        String httpOrgCreateTestRtn = HttpClientUtil.doPost(httpOrgCreateTest, createMap, "utf-8");
        log.info("获取的结果是httpOrgCreateTestRtn：{}", httpOrgCreateTestRtn);
        if (StringUtils.isBlank(httpOrgCreateTestRtn)) {
            return "未获取到accesstoken:" + httpOrgCreateTest;
        }
        JSONObject jsonObj = JSONObject.fromObject(httpOrgCreateTestRtn);
        log.info("获取的结果是jsonObj：{}", jsonObj);

        String accessToken = jsonObj.getString("access_token");
        String openid = jsonObj.getString("openid");

        String userInfoUrl = USER_BASE_INFO_API.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openid);
        log.info("获取的结果是jsonObj：{}", userInfoUrl);

        Map<String, String> map = new HashMap<>();
        String userInfo = HttpClientUtil.doPost(userInfoUrl, map, "utf-8");
        JSONObject userInfoJson = JSONObject.fromObject(userInfo);
        log.info("获取到的用户基本信息是：{}", userInfoJson);

        // 存储用户
        ActorUser actor = new ActorUser();
        actor.setUid(UUID.randomUUID().toString().replaceAll("-", ""));
        actor.setOrgId(StringUtils.defaultString(orgId, "0"));
        actor.setOpenid(openid);
        actor.setGender(userInfoJson.getString("sex"));
        actor.setHeadImg(userInfoJson.getString("headimgurl"));
        actor.setStatus(0);
        actor.setNickName(userInfoJson.getString("nickname"));
        int successnNUm = actorService.insertActorUserWxInfo(actor);
        log.info("保存微信用户结果：{}", (successnNUm == 1 ? "Ok" : "No"));

        model.addAttribute("userInfo", userInfoJson);

        return "mp/userInfo";
    }
}