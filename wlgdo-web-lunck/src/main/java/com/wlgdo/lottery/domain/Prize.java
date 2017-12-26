package com.wlgdo.lottery.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Prize implements Serializable {

    private static final long serialVersionUID = 3475063338158289338L;

    public Prize() {
    }

    public Prize(String uid, String awardId) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.uid = uid;
        this.awardId = awardId;
        this.createTime = new Date();
        this.rewardIndex = System.nanoTime();
        this.status = 0;
    }

    private String id;
    private String uid;
    private String awardId;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:sss")
    private Date createTime;
    private Long rewardIndex;
    private Date receiveTime;
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getRewardIndex() {
        return rewardIndex;
    }

    public void setRewardIndex(Long rewardIndex) {
        this.rewardIndex = rewardIndex;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
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
