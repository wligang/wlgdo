package com.wlgdo.lottery.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Awards implements Serializable {
    private static final long serialVersionUID = -5447934204434079871L;
    private String            id;
    private String            orgId;
    private String            name;
    private String            level;
    private Integer           total;
    private Integer           sentNum;
    private Integer           status;                                  //奖品发送状态：0，未开始，1，已开始，2已结束
    private String            refuseUid;
    private String            keepUid;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date              createTime;
    private Date              updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSentNum() {
        return sentNum;
    }

    public void setSentNum(Integer sentNum) {
        this.sentNum = sentNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRefuseUid() {
        return refuseUid;
    }

    public void setRefuseUid(String refuseUid) {
        this.refuseUid = refuseUid;
    }

    public String getKeepUid() {
        return keepUid;
    }

    public void setKeepUid(String keepUid) {
        this.keepUid = keepUid;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
