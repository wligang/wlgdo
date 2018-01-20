package com.wlgdo.apartment.domain;

import java.io.Serializable;
import java.util.Date;

public class Owner implements Serializable {

	private static final long serialVersionUID = -1565115866276096035L;

	private String id;
	private String name;
	private String mobile;
	private String build;// 楼号
	private String floor;// 楼层
	private String room;// 房间号
	private String electfee;// 电费
	private String waterfee;// 水费

	private Date createtime;
	private Date updatetime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getElectfee() {
		return electfee;
	}

	public void setElectfee(String electfee) {
		this.electfee = electfee;
	}

	public String getWaterfee() {
		return waterfee;
	}

	public void setWaterfee(String waterfee) {
		this.waterfee = waterfee;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}
