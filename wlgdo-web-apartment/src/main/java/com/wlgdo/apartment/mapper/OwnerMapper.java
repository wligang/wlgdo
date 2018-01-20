package com.wlgdo.apartment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.wlgdo.apartment.domain.Owner;

public interface OwnerMapper {

	@Insert("insert into t_owner (id,name,mobile,build,unit,floor,room,electfee,waterfee,createtime,updatetime) "
			+ "values(#{id},#{name},#{mobile},#{build},1,#{floor},#{room},#{electfee},#{waterfee},now(),now())")
	int save(Owner user);

	List<Owner> query(Owner user);

	int update(Owner owner);

}
