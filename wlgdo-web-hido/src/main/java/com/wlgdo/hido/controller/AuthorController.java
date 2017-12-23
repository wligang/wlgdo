package com.wlgdo.hido.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wlgdo.common.utils.FileUtilz;
import com.wlgdo.common.utils.PropertiesUtils;
import com.wlgdo.hido.service.IAuthorService;
import com.wlgdo.hido.domain.UserPo;

@Controller
public class AuthorController extends BaseController {
	Logger log = LoggerFactory.getLogger(getClass());
	private static final String FILE_BASE_PATH = PropertiesUtils.getVal("data_path");
	private static final String HEADER_PATH = "header/";
	public static final String USER_INFO = "user";

	@Autowired
	private IAuthorService authorService;

	/**
	 * 去往登陆页面
	 * 
	 * @author wlg 2016年12月10日
	 * @return ModelAndView
	 */
	@RequestMapping("auth/tologin")
	public String toLogin(HttpServletRequest request, Model model) {
		request.getSession().setAttribute("redire", request.getParameter("redire"));
		// 判断设备类型，跳转到相应的登陆页面
		return "login";
	}

	/**
	 * 登陆
	 * 
	 * @author wlg 2016年12月10日
	 * @return ModelAndView
	 */
	@RequestMapping("auth/login")
	@ResponseBody
	public Map login(HttpServletRequest request, HttpServletResponse response, String accname, String password) {
		log.info("开始登录，待重定向页面是:{}", request.getSession().getAttribute("redire"));
		Map mp = new HashMap();
		mp.put("retCd", -1);
		UserPo user = new UserPo();
		user.setPassword(password);
		user.setAccname(accname);
		user = authorService.findUser(user);
		if (user != null) {
			mp.put("retCd", 0);
			request.getSession().setAttribute("login", true);
			request.getSession().setAttribute("uid", user.getUid());
			request.getSession().setAttribute(USER_INFO, user);

			for (UserPo u : authorService.findUserList("", true)) {
				userMap.put(u.getUid(), u.getAccname());
			}
			request.getSession().setAttribute(USER_MP, userMap);

			if (StringUtils.isNotBlank((CharSequence) request.getSession().getAttribute("redire"))) {
				String redire = (String) request.getSession().getAttribute("redire");
				mp.put("redire", redire);
			}
		}
		log.info("登录完成{}", mp);
		return mp;
	}

	/**
	 * 注册
	 */
	@RequestMapping("auth/reg")
	@ResponseBody
	public Map regiest(UserPo user, Model model, HttpServletRequest request) {
		ModelAndView mview = new ModelAndView();
		log.info("--注册流程--");
		Map map = new HashMap<String, Object>();
		map.put("retCd", -1);
		if (StringUtils.isBlank(user.getAccname()) || StringUtils.isBlank(user.getPassword())) {
			map.put("msg", "请检查用户名、密码，邮箱不能为空");
			return map;
		}

		if (!authorService.checkUserInfo("accname", user.getAccname())) {
			map.put("msg", "该用户名已被注册，请使用别的名称");
			return map;
		}
		map.put("retCd", authorService.saveUserInfo(user) != null ? 0 : -1);

		request.getSession().setAttribute("login", true);
		request.getSession().setAttribute("uid", user.getUid());
		request.getSession().setAttribute(USER_INFO, user);
		return map;
	}

	/**
	 * 登出
	 * 
	 * @author wlg 2016年12月10日
	 * @param user
	 * @param model
	 * @return Map
	 */
	@RequestMapping("auth/exit")
	@ResponseBody
	public Map exit(HttpServletRequest request) {
		Map map = new HashMap<String, Object>();
		request.getSession().setAttribute("login", false);
		request.getSession().removeAttribute("uid");
		System.out.println("uid:" + request.getSession().getAttribute("uid"));
		map.put("retCd", 0);
		return map;
	}

	/**
	 * 上传头像
	 * 
	 * @author wlg 2016年12月11日
	 * @param request
	 * @return Map
	 */
	@RequestMapping("auth/upImg")
	@ResponseBody
	public Map upheadImg(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map map = new HashMap<String, Object>();
		String uid = (String) request.getSession().getAttribute("uid");
		map.put("retCd", -1);
		String fpath = FileUtilz.upload(file, FILE_BASE_PATH + HEADER_PATH, uid);
		log.info("文件地址：{}", fpath);
		if (fpath != null) {
			map.put("retCd", 0);
		}
		return map;
	}

	/**
	 * 图片上传
	 * 
	 * @author wlgdo[wlgchun@163.com] 2016年12月25日
	 * @param file
	 * @param request
	 * @return
	 * @throws IOException
	 *             String
	 */
	public static String uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {
		System.out.println(file);
		String fileName = file.getOriginalFilename();
		File tempFile = new File(FILE_BASE_PATH, new Date().getTime() + String.valueOf(fileName));
		if (!tempFile.getParentFile().exists()) {
			tempFile.getParentFile().mkdir();
		}
		if (!tempFile.exists()) {
			tempFile.createNewFile();
		}
		file.transferTo(tempFile);
		return "/download?fileName=" + tempFile.getName();
	}

	/**
	 * 
	 * 获取用户列表
	 * 
	 * @author wlgdo[wlgchun@163.com] 2017年1月4日
	 * @param id
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping("auth/ulist")
	public ModelAndView userList(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		log.info("==========查询用户列表===========");
		String uid = (String) request.getSession().getAttribute("uid");
		String accname = "";
		// model.addObject("userlist",authorService.findUserList(uid,true));
		//
		// model.addObject("act",activityService.queryActivityListByid("1",
		// true).get(0));
		// model.addObject("ivt",activityService.queryActivityListByid("0",
		// true).get(0));
		List<Map<String, Object>> list = authorService.queryData("wang");
		log.info("页面通道去:{}", list);
		model.setViewName("wtb/usermanager");
		return model;
	}

	/**
	 * 受系统保护的公共的页面跳转通道
	 * 
	 * @author wlgdo[wlgchun@163.com] 2016年12月25日
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping("auth/page")
	public String page(String id, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		log.info("页面通道去:{}", id);
		return id.replaceFirst("views/", "");
	}
}