package com.wlgdo.lottery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlgdo.lottery.dao.OrgMapper;

@Service
public class OrgService {

    @Autowired
    OrgMapper orgMapper;

}
