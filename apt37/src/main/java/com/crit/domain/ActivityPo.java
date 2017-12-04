/**
 * TODO
 * 2017年1月5日
 * @author wlgdo[wlgchun@163.com]
 */
package com.crit.domain;

/**
 * 活动实体表
 * @author wlg 2017年1月5日
 */


public class ActivityPo {

	private String id;
	private String nm;
	private int acttime;//参与次数
	private int loadtime;//执行次数
	private String desc;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public int getActtime() {
		return acttime;
	}
	public void setActtime(int acttime) {
		this.acttime = acttime;
	}
	public int getLoadtime() {
		return loadtime;
	}
	public void setLoadtime(int loadtime) {
		this.loadtime = loadtime;
	}
	
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return "ActivityPo [id=" + id + ", nm=" + nm + ", acttime=" + acttime + ", loadtime=" + loadtime + ", desc="
				+ desc + "]";
	}

	
	
}
