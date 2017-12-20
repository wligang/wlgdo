package com.wlgdo.lottery.domain;

import java.util.UUID;

import com.wlgdo.common.model.User;

/**
 * 构建参与者域模型
 * 
 * @author: Ligang.Wang[wang_lg@suixingpay.com]
 * @date: 2017年11月20日 上午11:52:17
 * @Copyright ©2017 Suixingpay. All rights reserved.
 */
public class ActorUser extends User {

	private static final long serialVersionUID = -6954960645314935862L;
	private String employeeNo; // 员工编号
	private Integer status; // 参与者状态 0：未确认参加，1正常 2 暂时无法参与
	private String orgId; // 企业组织ID
	private String openid;
	private String headImg;
	private String wxBody;
	private String nickName;

	public ActorUser() {

	}

	public ActorUser(String orgId, String nickName, String openid, String headImg, String wxBody) {
		this.setUid(UUID.randomUUID().toString().replaceAll("-", ""));
		this.orgId = orgId;
		this.openid = openid;
		this.headImg = headImg;
		this.wxBody = wxBody;
		this.nickName = nickName;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getWxBody() {
		return wxBody;
	}

	public void setWxBody(String wxBody) {
		this.wxBody = wxBody;
	}

}
