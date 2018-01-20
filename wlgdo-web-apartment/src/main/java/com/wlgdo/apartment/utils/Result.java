package com.wlgdo.apartment.utils;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 业务结果返回封装类
 * 
 * @author: Ligang.Wang[wang_lg@suixingpay.com]
 * @date: 2017年11月20日 上午10:08:23
 * @Copyright ©2017 Suixingpay. All rights reserved.
 */
public class Result {

	private boolean success; // 请求是否成功
	private String msg;
	private String code;
	private Object data;

	Result() {
	};

	public Result(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
