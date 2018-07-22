package com.wlgdo.apartment.mapper;

import java.util.List;

import com.wlgdo.common.model.User;


public interface UserMapper {

	// @Insert("insert into user(username,age,time) values
	// (#{username},#{age},#{time})")
	public void addUser(User user);

	public List<User> list();

}