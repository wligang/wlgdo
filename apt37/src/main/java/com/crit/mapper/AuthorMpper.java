package com.crit.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AuthorMpper {

	 List<Map<String,Object>> queryData(@Param("accname")String accname);
	
}
