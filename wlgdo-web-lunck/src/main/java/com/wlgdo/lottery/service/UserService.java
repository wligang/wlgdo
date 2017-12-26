package com.wlgdo.lottery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlgdo.common.model.User;
import com.wlgdo.lottery.mapper.UserMapper;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public List<User> List() {
        return userMapper.list();
    }

}
