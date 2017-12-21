package com.wlgdo.lottery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlgdo.lottery.domain.Awards;
import com.wlgdo.lottery.mapper.AwardsMapper;

@Service
public class AwardaService {

    @Autowired
    AwardsMapper awardsMapper;

    public List<Awards> getAwardsListByOrgAndStatus(String orgId, int status) {
        return awardsMapper.getAwardsListByOrgAndStatus(orgId, status);
    }

    public int saveAwards(Awards award) {
        return awardsMapper.saveAwards(award);
    }

    public Awards getAwardsByOrgIdAndName(Awards award) {
        return awardsMapper.getAwardsByOrgIdAndName(award);
    }

    public Awards getAwardsById(String awardId) {
        return awardsMapper.getAwardsById(awardId);
    }

}
