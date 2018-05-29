package com.wlgdo.lottery.mapper;

import java.util.List;

import com.wlgdo.common.model.User;
import com.wlgdo.lottery.domain.ActorUser;

/**
 * @desc提供对user对象的相关操作
 * @author: Ligang.Wang[wlgchun@163.com]
 * @date: 2017年11月10日 下午3:49:02
 * @Copyright ©2017 Suixingpay. All rights reserved.
 */
public interface UserMapper {

    // @Insert("insert into user(username,age,time) values
    // (#{username},#{age},#{time})")
    public void addUser(User user);

    // @Select("select * from user where id = #{id}")
    public ActorUser selectUserById(int id);

    //@Select("select * from user")
    public List<User> list();

}