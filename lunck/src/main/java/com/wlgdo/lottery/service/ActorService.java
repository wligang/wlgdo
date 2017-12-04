package com.wlgdo.lottery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlgdo.lottery.domain.ActorUser;

import com.wlgdo.lottery.dao.ActorMapper;

/**
 * 参与者服务
 * 
 * @author: Ligang.Wang[wang_lg@suixingpay.com]
 * @date: 2017年11月20日 下午3:17:55
 * @Copyright ©2017 Suixingpay. All rights reserved.
 */
@Service
public class ActorService {

    @Autowired
    private ActorMapper actorMapper;

    public int insertActorUser(ActorUser actorUser) {
        return actorMapper.insertActorUser(actorUser);
    }

    public int updateActorUser(ActorUser actorUser) {
        return actorMapper.updateActorUser(actorUser);
    }

    public ActorUser getActorByEmployeeNo(String employee) {
        return actorMapper.getActorByEmployee(employee);
    }

    public List<ActorUser> getActoUserListByOrgId(String string) {
        return actorMapper.getActoUserListByOrgId(string);
    }

    public ActorUser getActorUserByUid(String uid) {
        return actorMapper.getActorUserByUid(uid);
    }

    public int insertActorUserWxInfo(ActorUser actor) {
        return actorMapper.insertActorUserWxInfo(actor);
    }

}
