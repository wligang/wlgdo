package com.wlgdo.apartment.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * 住户信息
 * 
 * @author Ligang.Wang[wlgchun@163.com]
 * @date 2018年1月20日下午9:31:33
 */
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

	public Owner() {

	}

	public Owner(String room, String electfee, String waterfee) {
		this.room = room;
		this.electfee = electfee;
		this.waterfee = waterfee;
		this.updatetime = new Date();
	}

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

	@DateTimeFormat(iso = ISO.DATE_TIME)
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
