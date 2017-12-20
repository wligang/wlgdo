package com.wlgdo.lottery.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wlgdo.common.model.User;
import com.wlgdo.lottery.domain.ActorUser;
import com.wlgdo.lottery.service.UserService;

/**
 * @desc 对User对象的业务提供服务，并在第二次上线之后才起作用
 * @author: Ligang.Wang[wang_lg@suixingpay.com]
 * @date: 2017年11月10日 下午3:50:23
 * @Copyright ©2017 Suixingpay. All rights reserved.
 */
@Controller
public class UserController {

	Logger log = LoggerFactory.getLogger(getClass());
	// 依赖注入
	@Autowired
	UserService userService;

	/**
	 * @desc [有效描述]
	 * @author Ligang.Wang[wang_lg@suixingpay.com]
	 * @date 2017年11月10日下午3:43:26
	 * @param model
	 * @param user
	 * @return String
	 */
	@RequestMapping("add")
	@ResponseBody
	public String add(Model model, ActorUser user) {
		// 设置用户注册时间
		// userService.addUser(user);
		log.info("注册用户");
		return "addUser";
	}

	@RequestMapping("user")
	public String addAll(ModelMap model) {
		log.info("info查询所有用户：{}", model);
		List<User> userAll = userService.List();

		model.addAttribute("userAll", userAll);
		return "Alluser";
	}

}