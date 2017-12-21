package com.wlgdo.lottery.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wlgdo.lottery.domain.Prize;
import com.wlgdo.lottery.mapper.PrizeMapper;

@Service
public class PrizeService {
    Logger      log = LoggerFactory.getLogger(getClass());
    @Autowired
    PrizeMapper prizeMapper;

    public int savePrize(Prize prize) {
        return prizeMapper.savePrize(prize);
    }

    public List<Prize> listPrize(Prize prize) {

        return prizeMapper.listPrize(prize);
    }

    @Transactional
    public int insertBatchPrize(String uids, String awardId) {
        String[] arrList = uids.split("-");
        List<Prize> prizeList = new ArrayList<Prize>(arrList.length);
        for (String p : arrList) {
            prizeList.add(new Prize(p, awardId));
        }
        log.info("封装好了中奖者列表：{}", prizeList);
        return prizeMapper.insertBatchPrize(prizeList);
    }
}
