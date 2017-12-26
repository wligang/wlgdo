package com.wlgdo.lottery.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wlgdo.common.utils.Resp;
import com.wlgdo.common.utils.Resp.RespCode;
import com.wlgdo.lottery.domain.ActorUser;
import com.wlgdo.lottery.domain.Awards;
import com.wlgdo.lottery.domain.Prize;
import com.wlgdo.lottery.service.ActorService;
import com.wlgdo.lottery.service.AwardaService;
import com.wlgdo.lottery.service.PrizeService;

/**
 * 
 * 
 * @author: Ligang.Wang[wang_lg@suixingpay.com] 
 * @date:  2017年11月27日 下午5:04:07
 * @Copyright ©2017 Suixingpay. All rights reserved.
 */
@RestController
public class PrizeController {
    Logger        log = LoggerFactory.getLogger(getClass());

    @Autowired
    PrizeService  prizeService;
    @Autowired
    AwardaService awardaService;
    @Autowired
    ActorService  actorService;

    @RequestMapping("prize/list")
    public Resp queryPrize(Prize prize) {
        PageHelper.startPage(1, 2);
        List<Prize> list = prizeService.listPrize(prize);
        if (list == null || list.isEmpty()) {
            return new Resp("-1", "查询奖品记录失败");
        }
        PageInfo<Prize> pageInfo = new PageInfo<Prize>(list);
        log.info("分页信息：{}", pageInfo);
        return new Resp(RespCode.SUCCESS, pageInfo);
    }

    @RequestMapping(value = ("prize/add"), method = RequestMethod.POST)
    public Resp savePrizeList(@RequestParam(required = true) String uids, @RequestParam(required = true) String awardId) {
        if (StringUtils.isBlank(uids) || StringUtils.isBlank(uids.replace("-", "")) || StringUtils.isBlank(awardId)) {
            return new Resp(RespCode.PARAM_ERROR);
        }

        int num = prizeService.insertBatchPrize(uids, awardId);
        if (num != 0) {
            return new Resp(RespCode.SUCCESS, num);
        }
        return new Resp("-1", "奖品记录失败");
    }

    @RequestMapping(value = ("prize/add/{uid}/{awardId}/"), method = RequestMethod.GET)
    public Resp savePrize(@PathVariable("uid") String uid, @PathVariable("awardId") String awardId) {

        Prize prize = new Prize(uid, awardId);

        //校验用户和奖项是否正常
        Awards awards = awardaService.getAwardsById(awardId);
        if (awards == null || awards.getStatus() != 1) {
            return new Resp("-1", "暂时无法参与该奖项");
        }
        ActorUser actor = actorService.getActorUserByUid(uid);
        if (actor == null) {
            return new Resp("-1", "未找到用户ID，请核实");
        }
        try {

            int success = prizeService.savePrize(prize);
            return new Resp((success - 1) + "", "0:创建成功，否则失败");
        } catch (Exception e) {
            log.error("用户添加奖品数据异常：{}，exception：{}", prize, e);
        }
        return new Resp("-1", "奖品记录失败");
    }
}
