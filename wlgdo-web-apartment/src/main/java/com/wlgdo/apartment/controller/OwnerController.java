package com.wlgdo.apartment.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wlgdo.apartment.domain.Owner;
import com.wlgdo.apartment.service.OwnerService;
import com.wlgdo.common.utils.PropertiesUtils;
import com.wlgdo.common.utils.Resp;
import com.wlgdo.common.utils.Resp.RespCode;

/**
 * @author Ligang.Wang[wlgchun@163.com]
 * @date 2018年1月20日下午7:13:35
 */
@RestController
public class OwnerController {
	private Logger log = LoggerFactory.getLogger(getClass());

	public String RESOURCE_FILE_PATH = PropertiesUtils.getVal("resourceFilePath");

	// 依赖注入
	@Autowired
	private OwnerService ownerrService;

	/**
	 * 导入用户
	 * 
	 * @author Ligang.Wang[wlgchun@163.com]
	 * @date 2018年1月20日下午9:06:15
	 * @param name
	 * @param room
	 * @return
	 */

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
	 * 查询用户信息
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

	/**
	 * 更新用户电表信息
	 * 
	 * @author Ligang.Wang[wlgchun@163.com]
	 * @date 2018年1月20日下午9:07:08
	 * @param room
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "owner/e/{room}/{electfee:.+}", method = RequestMethod.GET)
	public Object updateOwnerEletic(@PathVariable String room, @PathVariable String electfee) {
		log.info("更新用户电费：{},{}", room, electfee);
		Owner owner = ownerrService.update(new Owner(room, electfee, null));
		if (owner != null) {
			return new Resp(RespCode.SUCCESS, owner);
		}
		return new Resp("-1", "更新失败");
	}

	/**
	 * 
	 * [desc]
	 * 
	 * @author Ligang.Wang[wlgchun@163.com]
	 * @date 2018年1月20日下午9:33:42
	 * @param room
	 * @param electfee
	 * @return
	 */
	@RequestMapping(value = "owner/w/{room}/{waterfee:.+}", method = RequestMethod.GET)
	public Object updateOwnerWater(@PathVariable String room, @PathVariable String waterfee) {
		log.info("更新用户水费：{},{}", room, waterfee);
		Owner owner = ownerrService.update(new Owner(room, null, waterfee));
		if (owner != null) {
			return new Resp(RespCode.SUCCESS, owner);
		}
		return new Resp("-1", "更新失败");
	}

	/**
	 * 获取头像
	 * 
	 * @author Ligang.Wang[wlgchun@163.com]
	 * @date 2018年1月20日下午9:05:48
	 * @param room
	 * @param response
	 */
	@RequestMapping("/owner/img/{room}")
	public void getImage(@PathVariable String room, HttpServletResponse response) {
		String JPG = "image/jpeg;charset=GB2312";
		// 本地文件路径
		String filePath = RESOURCE_FILE_PATH + "headImg/" + File.separator + room + ".jpg";
		File file = new File(filePath);
		if (!file.exists()) {
			filePath = RESOURCE_FILE_PATH + "headImg/default.jpg";
			file = new File(filePath);
		}
		// 获取输出流
		OutputStream outputStream = null;
		FileInputStream fileInputStream = null;
		try {
			outputStream = response.getOutputStream();
			fileInputStream = new FileInputStream(file);
			// 读数据
			byte[] data = new byte[fileInputStream.available()];
			fileInputStream.read(data);

			// 回写
			response.setContentType(JPG);
			outputStream.write(data);
			fileInputStream.close();
			outputStream.flush();
			outputStream.close();

		} catch (IOException e) {
			log.error("头像读取异常：uid:{},{}", room, e);
		} finally {
			log.error("关闭流");
		}
	}

}