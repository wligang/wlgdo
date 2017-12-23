package com.wlgdo.hido.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wlgdo.common.utils.Resp;
import com.wlgdo.common.utils.Resp.RespCode;
import com.wlgdo.hido.domain.EssayPo;
import com.wlgdo.hido.service.IEssayService;

/**
 * 
 *
 * @author Ligang.Wang[wlgchun@163.com]
 * @date 2017年12月23日下午8:20:07
 */
@Controller
public class EssayController extends BaseController {

	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private IEssayService essayService;

	@RequestMapping("essay/open")
	public String essayList(HttpServletRequest request, Model model) {
		log.info("展示所有公开的文章");
		EssayPo essay = new EssayPo();
		essay.setIsOpen(0);
		List<EssayPo> lsit = essayService.queryEssayList(essay);
		model.addAttribute("datalist", lsit);
		return "wtb/essayList";
	}

	@RequestMapping("essay")
	public String essayList(HttpServletRequest request, HttpServletResponse response, Model model) {
		log.info("个人中心文章展示");
		String uid = (String) request.getSession().getAttribute("uid");
		if (StringUtils.isBlank(uid)) {
			log.info("个人中心未登录");
			return "login";
		}
		List<EssayPo> lsit = essayService.queryEssayListByid(uid, false);
		model.addAttribute("datalist", lsit);
		return "/wtb/culture";
	}

	/**
	 * 
	 * 创建文章内容
	 * 
	 * @author wlgdo[wlgchun@163.com] 2016年12月31日
	 * @param request
	 * @param response
	 * @param model
	 * @return Object
	 */
	@RequestMapping("essay/save")
	@ResponseBody
	public Object getSuggest(HttpServletRequest request, HttpServletResponse response, EssayPo essay) {
		log.info("用户提交文章：{}", essay);
		String uid = (String) request.getSession().getAttribute("uid");
		if (StringUtils.isBlank(uid)) {
			return new Resp(RespCode.LOGIN_ERROR.getCode(), "请先登录");
		}

		essay.setUid(uid);
		if (StringUtils.isBlank(essay.getImgurl()) && StringUtils.isBlank(essay.getContext())) {
			return new Resp(RespCode.LOGIN_ERROR.getCode(), "图片和文字不能同时为空");
		}

		// 开始保存
		essayService.saveEssay(essay);
		return new Resp(RespCode.SUCCESS, essay);
	}

	/**
	 * 编辑信息
	 * 
	 * @author wlgdo[wlgchun@163.com] 2016年12月31日
	 * @param request
	 * @param response
	 * @param model
	 * @return Object
	 */
	@RequestMapping("essay/edit")
	@ResponseBody
	public Object getSuggest(HttpServletRequest request, HttpServletResponse response, Model model) {
		String suggest = request.getParameter("suggest");
		log.info("收到页面发来的建议：{}", suggest);
		return new Resp(RespCode.SUCCESS, suggest);
	}

	/**
	 * 
	 * 保存
	 * 
	 * @author wlgdo[wlgchun@163.com] 2017年1月7日
	 * @param request
	 * @param response
	 * @param model
	 * @return Object
	 */
	@RequestMapping("essay/zan")
	@ResponseBody
	public Object addZan(HttpServletRequest request, HttpServletResponse response, Model model) {
		String essayId = request.getParameter("id");
		String uid = getUserId(request, response);
		int zanNum = 0;
		if (StringUtils.isBlank(uid)) {
			log.info("未登录的用在点赞");
		} else {
			log.info("用户:{}在赞:{}", essayId, uid);
			zanNum = essayService.saveEssayZan(essayId, uid);
		}
		return new Resp(RespCode.SUCCESS, zanNum);
	}

	/**
	 * 
	 * 保存
	 * 
	 * @author wlgdo[wlgchun@163.com] 2017年1月7日
	 * @param request
	 * @param response
	 * @param model
	 * @return Object
	 */
	@RequestMapping("essay/addComment")
	@ResponseBody
	public Object addComment(HttpServletRequest request, HttpServletResponse response, Model model) {
		String essayId = request.getParameter("id");
		String uid = getUserId(request, response);
		String context = (String) request.getParameter("context");
		log.info("用户:{}在评论:{}:{}", uid, essayId, context);
		int commNum = essayService.saveComent(context, essayId, uid);
		return getRetMap(commNum, commNum > -1 ? commNum : -1, "操作成功");
	}

}