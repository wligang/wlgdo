package com.crit.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crit.domain.UserPo;

/**
 * Controller的基类
 * 2017年1月7日
 * @author wlgdo[wlgchun@163.com]
 */
public abstract class BaseController {
	
	
	// 错误编码
	public static final String ERR_CODE = "err";
	// 错误信息
	public static final String ERR_MSG = "errMsg";
	
	protected Map<String, Object> session;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	//业务session key
	public static final String USER_INFO = "user";
	public static final String USER_ID="uid";
	
	
	protected static  Map<String,Object> retMap;
	
	
	//用户昵称字典
	public static final String USER_MP="userMp";
	public Map<String,Object> userMap=new HashMap<>();
	
	
	/**
	 * 请求的返回结果
	 */
	private Object result;
	
	/**
	 * 
	 * 获取用户信息
	 * @author <a href='http://www.wlgdo.com'>王利刚(wlgchun@163.com)</a> Copy Right wlgdo.com 2017年1月7日
	 * @param request
	 * @param response
	 * @return UserPo
	 */
	public UserPo getUser(HttpServletRequest request,HttpServletResponse response) {
		return (UserPo)request.getSession().getAttribute(USER_INFO);
	}
	/**
	 * 
	 * 获取用户ID
	 * @author <a href='http://www.wlgdo.com'>王利刚(wlgchun@163.com)</a> Copy Right wlgdo.com 2017年1月7日
	 * @param request
	 * @param response
	 * @return String
	 */
	public String getUserId(HttpServletRequest request,HttpServletResponse response) {
		return (String)request.getSession().getAttribute(USER_ID);
	}
	
	/**
	 * 定义简单的返回封装Map
	 * @author <a href='http://www.wlgdo.com'>王利刚(wlgchun@163.com)</a> Copy Right wlgdo.com 2017年1月7日 
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> getRetMap() {
		Map<String, Object> retMap=new HashMap<String, Object>();
		retMap.put("retCd", -1);
		retMap.put("msg", "操作失败");
		return retMap;
	}
	
	public static Map<String, Object> getRetMap(Object data,int code,String msg) {
		Map<String, Object> retMap=new HashMap<String, Object>();
		retMap.put("data", data);
		retMap.put("retCode", code);
		retMap.put("msg", msg);
		return retMap;
	}
	public static Map<String, Object> getRetMap(int code) {
		Map<String, Object> retMap=new HashMap<String, Object>();
		retMap.put("retCd", code);
		return retMap;
	}
	
	
	/**
	 * 返回HTTP请求处理成功结果
	 */
	public void setSuccResult(Map<String, Object> result) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ERR_CODE, 0);
		if (result != null) {
			map.putAll(result);
		}
		setResult(map);
	}
	
	/**
	 * 返回HTTP请求处理出错的结果
	 */
	public void setErrResult(Map<String, Object> result) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ERR_CODE, 1);
		map.putAll(result);
		setResult(map);
	}
	
	/**
	 * 返回HTTP请求处理出错的结果
	 */
	public void setErrResult(String errMsg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ERR_CODE, 1);
		map.put(ERR_MSG, errMsg);
		setResult(map);
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	
	public Map<String, Object> getUserMap() {
		return userMap;
	}
	public void setUserMap(Map<String, Object> userMap) {
		this.userMap = userMap;
	}
	
	
	
	
	
}
	