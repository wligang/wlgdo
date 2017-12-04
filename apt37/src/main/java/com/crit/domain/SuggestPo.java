package com.crit.domain;

import java.util.Date;

public class SuggestPo {

	private String _id;
	private String suggest;
	private String actName;
	private String accConnect;
	private Date ctime;
	private Date utime;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getSuggest() {
		return suggest;
	}
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getAccConnect() {
		return accConnect;
	}
	public void setAccConnect(String accConnect) {
		this.accConnect = accConnect;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public Date getUtime() {
		return utime;
	}
	public void setUtime(Date utime) {
		this.utime = utime;
	}
	
	@Override
	public String toString() {
		return "SuggestPo [_id=" + _id + ", suggest=" + suggest + ", actName=" + actName + ", accConnect=" + accConnect
				+ ", ctime=" + ctime + ", utime=" + utime + "]";
	}
	
	
}
