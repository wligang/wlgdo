package com.wlgdo.hido.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface LoginMapper {

    @Select("select * from t_actor  limit 1")
    Map<String, Object> login(String string);

}
