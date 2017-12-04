package com.wlgdo.lottery.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wlgdo.lottery.domain.ActorUser;

public interface ActorMapper {

    @Insert("insert into t_actor(id,employee_no,email,name,gender,status,org_id) "
            + "values(#{uid},#{employeeNo},#{email},#{name},#{gender},#{status},#{orgId})")
    int insertActorUser(ActorUser actUser);

    @Update("update t_actor set mobile=#{mobile}, status=${status} where id=#{uid}")
    int updateActorUser(ActorUser actorUser);

    @Select("select * from t_actor where employee_no=#{employeeNo}")
    ActorUser getActorByEmployee(String employee);

    @Select("select id uid,employee_no employeeNo,mobile,name,status,head_img headImg,wx_Body wxBody,openid from t_actor where org_id=#{orgId}")
    List<ActorUser> getActoUserListByOrgId(String string);

    @Select("select id uid,employee_no employeeNo,mobile,name,status,head_img headImg,wx_Body wxBody,openid from t_actor where org_id=#{orgId} and mobile=#{mobile}")
    ActorUser getActorByMobile(String orgId, String mobile);

    @Select("select employee_no employeeNo,mobile,name,status from t_actor where id=#{uid}")
    ActorUser getActorUserByUid(String uid);

    @Insert("insert into t_actor(id,gender,status,org_id,nick_name,openid,head_img,wx_body) "
            + "values(#{uid},#{gender},#{status},#{orgId},#{nickName},#{openid},#{headImg},#{wxBody})")
    int insertActorUserWxInfo(ActorUser actor);
}
