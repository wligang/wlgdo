package com.wlgdo.apartment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlgdo.apartment.mapper.UserMapper;
import com.wlgdo.common.model.User;

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;

	public List<User> List() {
		return userMapper.list();
	}

}
