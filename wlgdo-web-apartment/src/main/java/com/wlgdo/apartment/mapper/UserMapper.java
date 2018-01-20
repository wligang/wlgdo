package com.wlgdo.apartment.mapper;

import java.util.List;

import com.wlgdo.common.model.User;

/**
 * @desc提供对user对象的相关操作
 * @author: Ligang.Wang[wang_lg@suixingpay.com]
 * @date: 2017年11月10日 下午3:49:02
 * @Copyright ©2017 Suixingpay. All rights reserved.
 */
public interface UserMapper {

	// @Insert("insert into user(username,age,time) values
	// (#{username},#{age},#{time})")
	public void addUser(User user);

	public List<User> list();

}