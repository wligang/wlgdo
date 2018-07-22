package com.wlgdo.oauth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wlgdo.common.utils.PropertiesUtils;

@Controller
public class OAuthController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private static String OAUTH_URL = "http://api.csdn.net/oauth2/authorize? client_id=YOUR_API_KEY&redirect_uri=YOUR_CALLBACK_URL&response_type=code";
	private static String ACCESS_TOKEN_URL = "http://api.csdn.net/oauth2/access_token? client_id=YOUR_API_KEY&client_secret=YOUR_API_SECRET&grant_type=authorization_code &redirect_uri=YOUR_CALLBACK_URL&code=THE_CODE_FROM_ABOVE";

	private static String APP_KEY = "1100788";
	private static String APP_SECRET = "501f152be6a742f5915acd656310a8b3";

	@RequestMapping("/oauth")
	public Object authIndex() {
		logger.info("开始调用");
		String domainName = PropertiesUtils.getVal("app.domain");
		String redirect_uri = OAUTH_URL.replaceFirst("YOUR_API_KEY", APP_KEY).replaceAll("YOUR_CALLBACK_URL", domainName + "/oauth/code");
		return "redirect:" + redirect_uri;
	}

	@RequestMapping("/code")
	public Object callBack(String code) {
		logger.info("get the code is :{}", code);
		return code;
	}
}