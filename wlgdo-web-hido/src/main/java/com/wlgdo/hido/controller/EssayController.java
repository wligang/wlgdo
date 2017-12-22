package com.wlgdo.hido.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.wlgdo.hido.domain.EssayPo;
import com.wlgdo.hido.service.IEssayService;

/**
 * 文章业务
 * 
 * @author wlg 2016年12月31日
 */
@Controller
public class EssayController extends BaseController{
	static final Logger log = LoggerFactory.getLogger(EssayController.class);

	@Autowired
	private IEssayService essayService;

	@RequestMapping("essay/query")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		log.info("文章展示");
		List<EssayPo> lsit=essayService.queryEssayListByid((String)request.getSession().getAttribute("uid"),false);
		
		model.addObject("datalist", lsit);
		model.setViewName("/wtb/culture");
		return model;
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
		Map<String, Object> map = new HashMap<>();
		essay.setUid((String)request.getSession().getAttribute("uid"));
		if(StringUtils.isBlank(essay.getImgurl()) && StringUtils.isBlank(essay.getContext())){
			map.put("retCd", -1);
			map.put("msg", "图片和文字不能同时为空");
			return map;
		}
		//开始保存
		essayService.saveEssay(essay);
		map.put("retCd", 0);
		map.put("essay", essay);
		return map;
	}

	/**
	 * 编辑信息
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
		Map map = new HashMap<>();
		map.put("code", -1);
		log.info("收到页面发来的建议：{}", suggest);
		return map;
	}
	
	/**
	 * 
	 * 保存
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
		log.info("用户:{}在赞:{}",essayId ,uid);
		int zanNum=essayService.saveEssayZan(essayId,uid);
		return getRetMap(zanNum,zanNum>-1?zanNum:-1,"操作成功");
	}
	
	/**
	 * 
	 * 保存
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
		String uid = getUserId(request,response);
		String context=(String)request.getParameter("context");
		log.info("用户:{}在评论:{}:{}",uid,essayId ,context);
		int commNum=essayService.saveComent(context,essayId,uid);
		return getRetMap(commNum,commNum>-1?commNum:-1,"操作成功");
	}

}