package com.wlgdo.lottery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlgdo.lottery.dao.OrgMapper;
import com.wlgdo.lottery.domain.OrgInfo;

@Service
public class OrgService {

	@Autowired
	OrgMapper orgMapper;

	public OrgInfo getOrgInfoById(int id) {
		return orgMapper.getOrgInfoById(id);
	}

	public int insertOrgInfo(OrgInfo org) {
		return orgMapper.insertOrgInfo(org);
	}

	public List<OrgInfo> getOrgList() {
		return orgMapper.getOrgList();
	}

	public int updateOrg(OrgInfo org) {
		return orgMapper.updateOrg(org);
	}

}
