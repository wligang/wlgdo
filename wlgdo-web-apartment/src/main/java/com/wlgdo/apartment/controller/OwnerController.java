package com.wlgdo.apartment.controller;

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
	public Object saveOwner(@PathVariable String name, @PathVariable String room, Owner user) {
		log.info("注册用户");
		user.setFloor(room.charAt(0) + "");
		user.setBuild("6");
		user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		int x = ownerrService.save(user);
		log.info("保存用户结果：{}", x);
		return new Resp(RespCode.SUCCESS, user);
	}

}