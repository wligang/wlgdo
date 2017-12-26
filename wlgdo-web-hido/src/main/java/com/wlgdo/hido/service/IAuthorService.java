package com.wlgdo.hido.service;

import java.util.List;
import java.util.Map;

import com.wlgdo.hido.domain.UserPo;

public interface IAuthorService {
	
	List<Map<String, Object>> queryData(String sql);
	 
	 UserPo findUser(UserPo user);
	 
	 boolean saveSuggest(String suggest,String connect);
	 
	 UserPo  saveUserInfo(UserPo user);

	/**
	 * @author wlgdo[wlgchun@163.com] 2017年1月1日 
	 * @param string
	 * @param accname
	 * @return boolean
	 */
	boolean checkUserInfo(String string, Object value);

	/**
	 * 查询用户列表
	 * @author wlgdo[wlgchun@163.com] 2017年1月4日 
	 * @param uid
	 * @param b
	 * @return Object
	 */
	List<UserPo> findUserList(String uid, boolean b);
}
