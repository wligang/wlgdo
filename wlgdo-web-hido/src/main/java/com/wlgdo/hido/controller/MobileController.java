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

import com.wlgdo.hido.domain.EssayPo;
import com.wlgdo.hido.domain.UserPo;
import com.wlgdo.hido.service.IEssayService;

@Controller
public class MobileController {
    Logger                log = LoggerFactory.getLogger(getClass());
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
    public String index(HttpServletRequest request, HttpServletResponse response) {
        log.info("-----------进入后台应用页面-------sessionid:{}", request.getSession().getId());

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
        String id = request.getParameter("id");
        String uid = (String) request.getSession().getAttribute("uid");
        log.info("开始转发页面：{}", id);
        boolean isOwner = StringUtils.isNotBlank(request.getParameter("flat"));
        switch (id) {
            case "culture":
                Object r = request.getSession().getAttribute(BaseController.USER_MP);
                log.info("用户：{}", r);
                List<EssayPo> lsit = essayService.queryEssayListByid(uid, isOwner);
                model.addAttribute("datalist", lsit);
                break;
        }
        model.addAttribute("user", (UserPo) request.getSession().getAttribute(AuthorController.USER_INFO));
        return "/wtb/" + id;
    }

}