package com.wlgdo.hido.domain;

import java.util.Date;

public class UserPo {

    private String uid;
    private String accname;
    private String password;
    private String declaration;
    private String email;
    private String url;
    private String phone;
    private String gender;
    private String nick;
    private String hobby;
    private Date   ctime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAccname() {
        return accname;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "UserPo [uid=" + uid + ", accname=" + accname + ", password=" + password + ", declaration=" + declaration
               + ", email=" + email + ", phone=" + phone + ", gender=" + gender + ", nick=" + nick + ", hobby=" + hobby
               + ", ctime=" + ctime + "]";
    }

}
