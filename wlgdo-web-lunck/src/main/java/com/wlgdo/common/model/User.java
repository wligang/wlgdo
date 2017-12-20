package com.wlgdo.common.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 构建一个用来抽象类来构造用户的基本属性
 * @author: Ligang.Wang[wang_lg@suixingpay.com] 
 * @date:  2017年11月20日 上午11:52:17
 * @Copyright ©2017 Suixingpay. All rights reserved.
 */
public class User implements Serializable {

    private static final long serialVersionUID = -6813759639777340481L;
    private String            uid;                                     //用户uuid，基于本平台的
    private String            name;                                    //用户名称
    private String            gender;                                  //性别
    private String            mobile;                                  //用户的手机号
    private String            email;                                   //用户的邮箱
    private Integer           age;                                     //年龄
    private String              birthday;                                //出生日期

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

  

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
