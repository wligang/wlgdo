package com.wlgdo.apartment.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wlgdo.apartment.domain.Owner;
import com.wlgdo.apartment.service.OwnerService;
import com.wlgdo.common.utils.Resp;
import com.wlgdo.common.utils.Resp.RespCode;

/**
 * @author Ligang.Wang[wlgchun@163.com]
 * @date 2018年1月20日下午7:13:35
 */
@RestController
public class OwnerController {

	private Logger log = LoggerFactory.getLogger(getClass());
	// 依赖注入
	@Autowired
	private OwnerService ownerrService;

	@RequestMapping(value = "owner/{name}/{room}", method = RequestMethod.GET)
	public Object saveOwner(@PathVariable String name, @PathVariable String room) {
		log.info("注册用户");
		Owner user = new Owner();
		user.setBuild("6");
		user.setRoom(room);
		List<Owner> olist = ownerrService.query(user);
		if (!olist.isEmpty()) {
			return new Resp("-1", "该房间已经注册了");
		}
		user.setName(name);
		user.setFloor(room.charAt(0) + "");
		user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		user.setElectfee("0.00");
		user.setWaterfee("0.00");
		int x = ownerrService.save(user);
		log.info("保存用户结果：{}", x);
		return new Resp(RespCode.SUCCESS, user);
	}

	/**
	 * 
	 * @author Ligang.Wang[wlgchun@163.com]
	 * @date 2018年1月20日下午8:02:18
	 * @return
	 */
	@RequestMapping(value = "owner/{room}", method = RequestMethod.GET)
	public Object saveOwner(@PathVariable String room, Owner user) {
		List<Owner> olist = ownerrService.query(user);
		log.info("查詢用戶信息：{}", olist);
		return new Resp(RespCode.SUCCESS, olist);
	}

}