package com.wlgdo.lottery.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wlgdo.common.utils.DESUtils;
import com.wlgdo.common.utils.EncryptionUtil;
import com.wlgdo.common.utils.Resp;
import com.wlgdo.common.utils.Resp.RespCode;
import com.wlgdo.lottery.domain.OrgInfo;
import com.wlgdo.lottery.service.OrgService;

/**
 * 组织机构管理服务
 * 
 * @author 王利刚[wlgchun@163.com]
 * @date 2017年12月5日下午8:39:19
 */
@RestController
public class OrgController {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private OrgService orgService;

	@RequestMapping(value = ("org"), method = RequestMethod.POST)
	public Resp saveOrgInfo(OrgInfo org) {
		if (org == null || org.getId() == null) {
			return new Resp("请提交正确的商户信息");
		}

		if (orgService.getOrgInfoById(org.getId()) != null) {
			return new Resp("该机构Id已被注册");
		}
		org.setToken(DESUtils.encrypt(org.getId() + "", EncryptionUtil.DES_KEY));
		org.setStatus(0);
		int num = orgService.insertOrgInfo(org);
		log.info("用户创建结果：{}", num);
		if (num == 0) {
			org.setToken(null);
			return new Resp(RespCode.FAIL, org);
		}
		return new Resp(RespCode.SUCCESS, org);
	}

	/**
	 * @author Ligang.Wang[wlgchun@163.com]
	 * @date 2017年12月5日下午10:16:31
	 * @return 全部列表
	 */
	@RequestMapping(value = ("org"), method = RequestMethod.GET)
	public Resp listOrgInfo() {
		PageHelper.startPage(1, 2);
		List<OrgInfo> list = orgService.getOrgList();
		PageInfo<OrgInfo> pageInfo = new PageInfo<OrgInfo>(list);
		return new Resp(RespCode.SUCCESS, pageInfo);
	}

	/**
	 * @author Ligang.Wang[wlgchun@163.com]
	 * @date 2017年12月5日下午10:16:53
	 * @param id
	 * @return 单独一个机构
	 */
	@RequestMapping(value = ("org/{id}"), method = RequestMethod.GET)
	public Resp listOrgInfoById(@PathVariable("id") Integer id) {
		return new Resp(RespCode.SUCCESS, orgService.getOrgInfoById(id));
	}

	/**
	 * 修改重定向路径
	 * 
	 * @author Ligang.Wang[wlgchun@163.com]
	 * @date 2017年12月5日下午11:08:12
	 * @param id
	 * @param url
	 * @return
	 */
	@RequestMapping(value = ("org/{id}"), method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
	public Resp upDateOrgInfoOauthRedirect(@PathVariable("id") Integer id, OrgInfo org, HttpServletRequest request) {

		// 校验URL正确性
		// if (org != null) {
		org.setId(id);
		String url = request.getParameter("backUrl");
		org.setBackUrl(url);
		int num = orgService.updateOrg(org);
		log.info("更新结果：{}", num);
		// }

		return new Resp(RespCode.SUCCESS, org);
	}
}
