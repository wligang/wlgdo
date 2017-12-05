package com.wlgdo.lottery.domain;

import java.io.Serializable;
import java.sql.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class OrgInfo implements Serializable {

    private static final long serialVersionUID = 456981164207187707L;
    private int               id;
    private String            orgName;
    private String            chargeName;
    private String            chargeMobile;
    private String            appid;
    private String            appsecret;
    private String            mpToken;
    private String            accessToken;
    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:sss")
    private Date              tokenFreshTime;                         // accessToken 更新时间7
    private String            createTime;
    private Integer           status;                                 // 0:正常，1:停用

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getMpToken() {
        return mpToken;
    }

    public void setMpToken(String mpToken) {
        this.mpToken = mpToken;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
