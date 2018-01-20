package com.wlgdo.apartment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wlgdo.apartment.domain.ActorUser;
import com.wlgdo.apartment.service.UserService;

/**
 * @desc 对User对象的业务提供服务，并在第二次上线之后才起作用
 * @author: Ligang.Wang[wang_lg@suixingpay.com]
 * @date: 2017年11月10日 下午3:50:23
 * @Copyright ©2017 Suixingpay. All rights reserved.
 */
@RestController
public class UserController {

	private Logger log = LoggerFactory.getLogger(getClass());
	// 依赖注入
	@Autowired
	private UserService userService;

	/**
	 * @desc [有效描述]
	 * @author Ligang.Wang[wang_lg@suixingpay.com]
	 * @date 2017年11月10日下午3:43:26
	 * @param model
	 * @param user
	 * @return String
	 */
	@RequestMapping(value = "owner", method = RequestMethod.GET)
	public String add(Model model, ActorUser user) {
		// 设置用户注册时间
		// userService.addUser(user);
		log.info("注册用户");
		return "addUser";
	}

}