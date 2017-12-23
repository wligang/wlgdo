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
import org.springframework.web.servlet.ModelAndView;

import com.wlgdo.hido.domain.EssayPo;
import com.wlgdo.hido.domain.UserPo;
import com.wlgdo.hido.service.IEssayService;

@Controller
public class MobileController {
	Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private IEssayService essayService;

	/**
	 * 也用后台主页面
	 * 
	 * @author wlg 2016年12月11日
	 * @return ModelAndView
	 */
	@RequestMapping("mob")
	public String login() {
		log.info("-----------进入后台应用页面-------");

		// Map<String, Object> map = SpringUtils.getBean(LoginMapper.class).login("");
		// log.info("查询结果：{}", map);

		return "login";
	}

	@RequestMapping("mob/index")
	public String index() {
		log.info("-----------进入后台应用页面-------");

		// Map<String, Object> map = SpringUtils.getBean(LoginMapper.class).login("");
		// log.info("查询结果：{}", map);

		return "wtb/index";
	}

	/**
	 * 应用页面转发
	 * 
	 * @author wlg 2016年12月11日
	 * @param request
	 * @param response
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping("mob/forward")
	public String forward(HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView mview = new ModelAndView();
		String id = request.getParameter("id");
		StringUtils.isNotBlank(request.getParameter(""));
		log.info("开始转发页面：{}", id);
		switch (id) {
		case "culture":
			Object r = request.getSession().getAttribute(BaseController.USER_MP);
			log.info("用户：{}", r);
			List<EssayPo> lsit = essayService.queryEssayListByid((String) request.getSession().getAttribute("uid"),
					StringUtils.isNotBlank(request.getParameter("flat")));
			mview.addObject("datalist", lsit);
			break;
		}

		StringUtils.defaultString(id, "welcome");
		model.addAttribute("user", (UserPo) request.getSession().getAttribute(AuthorController.USER_INFO));
		return "/wtb/" + id;
	}

}