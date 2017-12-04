package com.wlgdo.lottery.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wlgdo.common.utils.PropertiesUtil;
import com.wlgdo.common.utils.Resp;
import com.wlgdo.common.utils.Resp.RespCode;
import com.wlgdo.lottery.domain.ActorUser;
import com.wlgdo.lottery.service.ActorService;

/**
 * 参与者请求接受服务
 * 
 * @author: Ligang.Wang[wang_lg@suixingpay.com]
 * @date: 2017年11月20日 下午2:48:34
 * @Copyright ©2017 Suixingpay. All rights reserved.
 */
@RestController
public class ActorController {

    Logger log = LoggerFactory.getLogger(getClass());

    public String RESOURCE_FILE_PATH = PropertiesUtil.prop("resourceFilePath");

    /** ActorUser的session key */
    public static String ACT_USER = "act_user";

    @Autowired
    private ActorService actorService;

    /**
     * 
     * 查询该机构下所有参与用户
     * 
     * @author Ligang.Wang[wang_lg@suixingpay.com]
     * @date 2017年11月23日上午10:52:11
     * @param orgid
     *            机构ID
     * @return Resp 请求封装类
     */
    @RequestMapping("/actor/list/{orgid}")
    public Resp actorList(@PathVariable("orgid") String orgid) {
        log.info("info查询该机构下所有用户：{}", orgid);
        List<ActorUser> actorUsers = actorService.getActoUserListByOrgId(orgid);
        log.info("列表：{}", actorUsers);
        return new Resp(RespCode.SUCCESS, actorUsers);
    }

    /**
     * 添加员工
     * 
     * @author Ligang.Wang[wang_lg@suixingpay.com]
     * @date 2017年11月22日下午12:20:36
     * @param employee
     *            雇员编号
     * @param name
     *            姓名
     * @param orgId
     *            机构ID
     * @return Object
     */
    @RequestMapping("/actor/add/{employee}/{name}/{orgId}")
    public Object addActor(@PathVariable("employee") String employee, @PathVariable("name") String name,
            @PathVariable("orgId") String orgId) {
        ActorUser actorUser = new ActorUser();
        actorUser.setUid(UUID.randomUUID().toString().toLowerCase().replace("-", ""));
        actorUser.setName(name);
        actorUser.setEmployeeNo(employee);
        actorUser.setOrgId(orgId);
        actorUser.setStatus(0);
        int successNum = actorService.insertActorUser(actorUser);
        return new Resp(RespCode.SUCCESS, successNum);
    }

    /**
     * 开始参与
     * 
     * @author Ligang.Wang[wang_lg@suixingpay.com]
     * @date 2017年11月20日下午5:19:22
     * @param employee
     * @return Resp
     */
    @RequestMapping("/actor/enroll/{employee}")
    public Resp enrollByEmployeeNo(@PathVariable("employee") String employee, HttpServletRequest request) {
        // 先找到这个工号
        ActorUser actorUser = actorService.getActorByEmployeeNo(employee);
        if (actorUser != null) {
            request.getSession().setAttribute(ACT_USER, actorUser); // 存入session
            actorUser.setEmployeeNo(employee);
            actorUser.setStatus(1);
            int successNum = actorService.insertActorUser(actorUser);
            log.info("报名完成：{},{}", successNum, actorUser);
            if (successNum == 1) {
                return new Resp(RespCode.SUCCESS);
            }
            return new Resp("-1", "报名失败");
        }
        return new Resp("-1", "未找到该员工编号");
    }

    /**
     * 手机认证
     * 
     * @author Ligang.Wang[wang_lg@suixingpay.com]
     * @date 2017年11月20日下午5:19:49
     * @param mobile
     * @return Resp
     */
    @RequestMapping("/actor/authentic/{mobile}")
    public Resp authenticByMobile(@PathVariable("mobile") String mobile, HttpServletRequest request) {
        // 获取当前会话对象
        ActorUser actorUser = (ActorUser) request.getSession().getAttribute(ACT_USER);
        if (actorUser == null) {
            return new Resp("-1", "无法获取当前对象，请返回重试");
        }
        actorUser.setMobile(mobile);
        actorUser.setStatus(2);
        int successNum = actorService.updateActorUser(actorUser);
        return successNum == 1 ? new Resp(RespCode.SUCCESS) : new Resp("添加失败");
    }

    @RequestMapping("/image/{orgId}/{id}")
    @ResponseBody
    public void getImage(@PathVariable("id") String id, @PathVariable("orgId") String orgId, HttpServletResponse response) {
        String JPG = "image/jpeg;charset=GB2312";
        if (StringUtils.isNotEmpty(id)) {
            // 本地文件路径
            String filePath = RESOURCE_FILE_PATH + "headImg/" + orgId + File.separator + id + ".jpg";
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
                log.error("头像读取异常：uid:{},{}", id, e);
            } finally {
                log.error("关闭流");
            }
        }

    }
}
