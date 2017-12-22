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

import com.wlgdo.common.utils.Resp;
import com.wlgdo.common.utils.Resp.RespCode;
import com.wlgdo.hido.service.IActivityService;
import com.wlgdo.hido.service.IAuthorService;

@Controller
public class ActivityController {
    static final Logger      log = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private IAuthorService   authorService;

    @Autowired
    private IActivityService activityService;

    /**
     * 建议入口
     * 
     * @author wlg 2016年12月11日
     * @param request
     * @param response
     * @param model
     * @return Object
     */
    @RequestMapping("act/suggest")
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
    @RequestMapping("act/acttime")
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
    @RequestMapping("act/loadtime")
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
    @RequestMapping("act/loadjokes")
    @ResponseBody
    public Object loadjokes(HttpServletRequest request, HttpServletResponse response, Model model) {
        String actid = request.getParameter("actid");
        Map<Object, Object> map = new HashMap<>();
        map.put("code", -1);
        log.info("收到活动参与消息id:{}", actid);

        return map;
    }

    @RequestMapping("/act/addWord")
    @ResponseBody
    public Object addWord(HttpServletRequest request, HttpServletResponse response, Model model) {
        String actid = StringUtils.defaultString(request.getParameter("actid"), "3");
        log.info("收到添加关键词:id:{}", actid);
        String uid = StringUtils.defaultString((String) request.getSession().getAttribute("uid"), "0");
        String words = request.getParameter("words");
        String[] wordArr = activityService.addWords(words, uid);
        return new Resp(RespCode.SUCCESS, wordArr);
    }

    @RequestMapping("/act/words")
    public String queryActWords(HttpServletRequest request, HttpServletResponse response, Model model) {
        String actid = StringUtils.defaultString(request.getParameter("actid"), "3");
        log.info("查询关键词:id:{}", actid);
        String uid = StringUtils.defaultString((String) request.getSession().getAttribute("uid"), "0");
        String words = request.getParameter("words");
        String[] maplist = activityService.queryWords(words, uid);
        model.addAttribute("", maplist);
        return "/yyy/guess";
    }

}