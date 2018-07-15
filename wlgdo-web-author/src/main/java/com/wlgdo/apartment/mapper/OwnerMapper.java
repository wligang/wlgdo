package com.wlgdo.apartment.mapper;

import java.util.List;

import com.wlgdo.apartment.domain.Owner;

public interface OwnerMapper {

	int save(Owner user);

	List<Owner> query(Owner user);

	int update(Owner owner);

}
