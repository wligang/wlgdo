package com.wlgdo.hido.controller;

import java.util.HashMap;
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

import com.wlgdo.hido.service.IActivityService;
import com.wlgdo.hido.service.IAuthorService;

@Controller
@RequestMapping("/act")
public class ActivityController extends BaseController {
    static final Logger      log = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private IAuthorService   authorService;

    @Autowired
    private IActivityService activityService;

    @RequestMapping("sent.do")
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        log.info("start sent");
        model.setViewName("/index.jsp");
        return model;
    }

    @RequestMapping("exit.do")
    public ModelAndView exit() {
        ModelAndView model = new ModelAndView();

        model.setViewName("/author/authorSuccess.jsp");
        return model;
    }

    /**
     * 建议入口
     * 
     * @author wlg 2016年12月11日
     * @param request
     * @param response
     * @param model
     * @return Object
     */
    @RequestMapping("suggest.do")
    @ResponseBody
    public Object getSuggest(HttpServletRequest request, HttpServletResponse response, Model model) {
        String suggest = request.getParameter("suggest");
        Map<Object, Object> map = new HashMap<>();
        map.put("code", -1);
        log.info("收到页面发来的建议：{}", suggest);
        if (StringUtils.isNotBlank(suggest) && !"Enter Your Suggest".equals(suggest)) {
            log.info("保存该条建议");
            if (authorService.saveSuggest(suggest, "sugguest")) {
                map.put("code", 0);
            }
        }
        ;
        return map;
    }

    /**
     * 
     * 活动执行次数
     * 
     * @author wlgdo[wlgchun@163.com] 2017年1月5日
     * @param request
     * @param response
     * @param model
     * @return Object
     */
    @RequestMapping("acttime.do")
    @ResponseBody
    public Object acttime(HttpServletRequest request, HttpServletResponse response, Model model) {
        String actid = request.getParameter("actid");
        Map<Object, Object> map = new HashMap<>();
        log.info("收到活动:{}参与者消息id:", actid);
        map.put("rt", activityService.saveActivityTimeByparams(actid, 1));
        return map;
    }

    /**
     * 
     * 活动点级次数
     * 
     * @author wlgdo[wlgchun@163.com] 2017年1月5日
     * @param request
     * @param response
     * @param model
     * @return Object
     */
    @RequestMapping("loadtime.do")
    @ResponseBody
    public Object loadtime(HttpServletRequest request, HttpServletResponse response, Model model) {
        String actid = request.getParameter("actid");
        Map<Object, Object> map = new HashMap<>();
        map.put("code", -1);
        log.info("收到活动参与消息id:{}", actid);
        map.put("rt", activityService.saveActivityTimeByparams(actid, 0));
        return map;
    }

    /**
     * 
     * 查询所有的jokes列表
     * 
     * @author wlgdo[wlgchun@163.com] 2017年1月5日
     * @param request
     * @param response
     * @param model
     * @return Object
     */
    @RequestMapping("loadjokes.do")
    @ResponseBody
    public Object loadjokes(HttpServletRequest request, HttpServletResponse response, Model model) {
        String actid = request.getParameter("actid");
        Map<Object, Object> map = new HashMap<>();
        map.put("code", -1);
        log.info("收到活动参与消息id:{}", actid);

        return map;
    }

    @RequestMapping("addWord.do")
    @ResponseBody
    public Object addWord(HttpServletRequest request, HttpServletResponse response, Model model) {
        String actid = StringUtils.defaultString(request.getParameter("actid"), "3");
        log.info("收到添加关键词:id:{}", actid);
        String uid = StringUtils.defaultString((String) request.getSession().getAttribute("uid"), "0");
        String words = request.getParameter("words");
        String[] wordArr = activityService.addWords(words, uid);
        Map<String, Object> map = this.retMap;
        return map;
    }

    @RequestMapping("words.do")
    public ModelAndView queryActWords(HttpServletRequest request, HttpServletResponse response, Model model) {
        String actid = StringUtils.defaultString(request.getParameter("actid"), "3");
        log.info("查询关键词:id:{}", actid);
        String uid = StringUtils.defaultString((String) request.getSession().getAttribute("uid"), "0");
        String words = request.getParameter("words");
        String[] maplist = activityService.queryWords(words, uid);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/views/yyy/guess.jsp");
        return mv;
    }

}