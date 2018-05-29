package com.wlgdo.lottery.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wlgdo.common.utils.Resp;
import com.wlgdo.common.utils.Resp.RespCode;
import com.wlgdo.lottery.domain.Awards;
import com.wlgdo.lottery.service.AwardaService;

/**
 * 抽奖服务
 * @author: Ligang.Wang[wlgchun@163.com] 
 * @date:  2017年11月23日 下午3:08:59
 * @Copyright ©2017 Suixingpay. All rights reserved.
 */
@RestController
public class DrawController {

    @Autowired
    private AwardaService awardaService;

    /**
     * 有效描述
     * @author Ligang.Wang[wlgchun@163.com]
     * @date 2017年11月23日下午6:02:22
     * @param orgId
     * @param status
     * @return Resp
     */
    @RequestMapping("award/list/{orgId}/{status}")
    public Resp findAwardsList(@PathVariable("orgId") String orgId, @PathVariable("status") String status) {

        List<Awards> awardList = awardaService.getAwardsListByOrgAndStatus(orgId, Integer.valueOf(status));
        if (awardList.isEmpty()) {
            return new Resp("未查询到奖项");
        }
        return new Resp(RespCode.SUCCESS, awardList);
    }

    /**
     * 有效描述
     * @author Ligang.Wang[wlgchun@163.com]
     * @date 2017年11月24日下午1:27:18
     * @param orgId
     * @param status
     * @return Resp
     */
    @RequestMapping("award/add")
    public Resp saveAwards(Awards award) {

        award.setId(UUID.randomUUID().toString().replace("-", ""));
        award.setSentNum(0);
        award.setStatus(award.getStatus() == null ? 0 : 1);

        Awards awardTmp = awardaService.getAwardsByOrgIdAndName(award);
        if (awardTmp != null) {
            return new Resp("该机构下已经存在该名称的奖项");
        }

        int successNum = awardaService.saveAwards(award);
        if (successNum != 1) {
            return new Resp("-1", "未成功创建奖项");
        }
        return new Resp(RespCode.SUCCESS, award.getId());
    }
}
