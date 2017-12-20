package com.wlgdo.hido.domain;

import java.util.Date;

/**
 * 评论
 * @author wlg
 */
public class CommentPo {
	
	//杂记id
	private String eid;
	//评论人id
	private  String  uid;//格式化投降文件地址
	private  String  context;
	private  Date  cTime;
	
	private CommentPo(){}
	
	public CommentPo(String context,String eid,String uid) {
		this.cTime=new Date();
		this.context=context;
		this.eid=eid;
		this.uid=uid;
	}
	
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Date getcTime() {
		return cTime;
	}
	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}
	
	
}
