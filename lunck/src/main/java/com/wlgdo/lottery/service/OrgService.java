package com.wlgdo.lottery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlgdo.lottery.dao.OrgMapper;
import com.wlgdo.lottery.domain.OrgInfo;

@Service
public class OrgService {

    @Autowired
    OrgMapper orgMapper;

    public OrgInfo getOrgInfoById(Integer org) {
        return orgMapper.getOrgInfoById(org);
    }

}
