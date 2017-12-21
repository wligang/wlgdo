package com.wlgdo.lottery.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wlgdo.lottery.domain.Awards;

public interface AwardsMapper {

    @Select("select id,org_id orgId,name,level,total,sent_num sentNum,status,create_time createTime,update_time updateTime,"
            + "refuse_uid refuseUid,keep_uid keepUid from t_awards where org_id=#{orgId} and status=${status}")
    List<Awards> getAwardsListByOrgAndStatus(@Param("orgId") String orgId, @Param("status") int status);

    @Insert("insert into t_awards (id,org_id ,name,level,total,sent_num,status,create_time,refuse_uid,keep_uid)"
            + "values(#{id},#{orgId},#{name},#{level},#{total},#{sentNum},#{status},now(),#{refuseUid},#{keepUid})")
    int saveAwards(Awards award);

    @Select("select id,org_id orgId,name,level,total,sent_num sentNum,status,create_time createTime,update_time updateTime "
            + "from t_awards where id=#{awardId}")
    Awards getAwardsById(String awardId);

    @Select("select id,org_id orgId,name,level,total,sent_num sentNum,status,create_time createTime,update_time updateTime,"
            + "refuse_uid refuseUid,keep_uid keepUid from t_awards where org_id=#{orgId} and name=#{name}")
    Awards getAwardsByOrgIdAndName(Awards award);

}
