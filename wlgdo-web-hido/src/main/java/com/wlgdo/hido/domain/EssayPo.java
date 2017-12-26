package com.wlgdo.hido.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @author wlg
 */
public class EssayPo{
	
	private String uid;
	private String id;
	private String context;
	private String imgurl;
	private int type;//0 文本，1：图片 ,2 图文
	private int isOpen;//0 公开 1:非公开
	private int idDel;
	private int isZan;//是否开启赞
	private int zanNum;
	private Date cTime;
	private Date uTime;
	private List<CommentPo> commenList;
	private List<String> likeList;
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}
	public int getIdDel() {
		return idDel;
	}
	public void setIdDel(int idDel) {
		this.idDel = idDel;
	}
	public int getIsZan() {
		return isZan;
	}
	public void setIsZan(int isZan) {
		this.isZan = isZan;
	}
	public int getZanNum() {
		return zanNum;
	}
	public void setZanNum(int zanNum) {
		this.zanNum = zanNum;
	}
	public Date getcTime() {
		return cTime;
	}
	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}
	public Date getuTime() {
		return uTime;
	}
	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}
	public List<CommentPo> getCommenList() {
		return commenList;
	}
	public void setCommenList(List<CommentPo> commenList) {
		this.commenList = commenList;
	}
	public List<String> getLikeList() {
		if(likeList==null){
			likeList=new ArrayList<String>();
		}
		return likeList;
	}
	public void setLikeList(List<String> likeList) {
		this.likeList = likeList;
	}
	
	@Override
	public String toString() {
		return "EssayPo [uid=" + uid + ", id=" + id + ", context=" + context  + ", type=" + type
				+ ", isOpen=" + isOpen + ", idDel=" + idDel + ", isZan=" + isZan + ", zanNum=" + zanNum + ", cTime="
				+ cTime + ", uTime=" + uTime + "]";
	}
	
	
	
	
	
}
