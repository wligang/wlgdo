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
@RequestMapping("/mob")
public class MobileController {
	static final Logger log = LoggerFactory.getLogger(MobileController.class);
	
	@Autowired
	private IEssayService essayService;
	
	/**
	 * 也用后台主页面 
	 * @author wlg 2016年12月11日
	 * @return ModelAndView
	 */
	@RequestMapping("index.do")
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		log.info("-----------进入后台应用页面-------");
		model.setViewName("/views/wtb/index.jsp");
		
		return model;
	}
	
	/**
	 * 应用页面转发
	 * @author wlg 2016年12月11日
	 * @param request
	 * @param response
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping("forward.do")
	public ModelAndView forward(HttpServletRequest request, HttpServletResponse response,Model model) {
		ModelAndView mview = new ModelAndView();
		String id=request.getParameter("id");
		StringUtils.isNotBlank(request.getParameter(""));
		log.info("开始转发页面：{}",id);
		
		switch(id){
			case "culture": 
				Object r = request.getSession().getAttribute(BaseController.USER_MP);
				System.out.println(r);
				List<EssayPo> lsit=essayService.queryEssayListByid((String)request.getSession().getAttribute("uid"),StringUtils.isNotBlank(request.getParameter("flat")));
				mview.addObject("datalist", lsit);
				break;
		}
		
		
		StringUtils.defaultString(id, "welcome");
		mview.setViewName("/views/wtb/"+id+".jsp");
		mview.addObject("user",(UserPo)request.getSession().getAttribute(AuthorController.USER_INFO));
		return mview;
	}



}