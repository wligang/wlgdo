package com.wlgdo.apartment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wlgdo.apartment.domain.Owner;
import com.wlgdo.apartment.mapper.OwnerMapper;

@Service
public class OwnerService {

	@Autowired
	private OwnerMapper ownerMapper;

	public int save(Owner user) {
		return ownerMapper.save(user);
	}

	public List<Owner> query(Owner user) {
		return ownerMapper.query(user);
	}

	public Owner update(Owner owner) {
		owner.setBuild("6");
		ownerMapper.update(owner);
		if (1 == ownerMapper.update(owner)) {
			return owner;
		}
		return null;
	}

}
