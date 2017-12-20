package com.wlgdo.lottery.domain;

import java.io.Serializable;
import java.sql.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class OrgInfo implements Serializable {

	private static final long serialVersionUID = 456981164207187707L;
	/** 机构是否可正常接入：0正常，1暂停 */
	public static final Integer STATUS_OK = 0;
	public static final Integer STATUS_ON = 1;

	private Integer id;
	private String orgName;
	private String chargeName;
	private String chargeMobile;
	private String appid;
	private String appsecret;
	private String token;
	private String accessToken;
	// @JsonFormat(pattern = "yyyy-MM-dd HH:mm:sss")
	private Date tokenFreshTime; // accessToken 更新时间7
	private Date createTime;
	private Integer status; // 0:正常，1:停用
	private String backUrl; // 授权完回调的页面

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public String getChargeMobile() {
		return chargeMobile;
	}

	public void setChargeMobile(String chargeMobile) {
		this.chargeMobile = chargeMobile;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getTokenFreshTime() {
		return tokenFreshTime;
	}

	public void setTokenFreshTime(Date tokenFreshTime) {
		this.tokenFreshTime = tokenFreshTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
