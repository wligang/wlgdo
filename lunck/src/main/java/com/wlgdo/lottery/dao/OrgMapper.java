package com.wlgdo.lottery.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wlgdo.lottery.domain.OrgInfo;

public interface OrgMapper {

	@Select("select id,org_name orgName,charge_name chargeName,charge_mobile chargeMobile,appid,appsecret,token, access_token accessToken,token_fresh_time tokenFreshTime,"
			+ "create_time createTime,status,backUrl from t_org where id=${id}")
	OrgInfo getOrgInfoById(@Param("id") int org);

	@Insert("insert into t_org(id,org_name,charge_name ,charge_mobile ,appid,appsecret,token,access_token ,token_fresh_time ,create_time ,status, backUrl) "
			+ "values(${id},#{orgName},#{chargeName},#{chargeMobile},#{appid},#{appsecret},#{token},#{accessToken},#{tokenFreshTime},#{createTime},#{status},#{backUrl})")
	int insertOrgInfo(OrgInfo org);

	@Select("select id,org_name orgName,charge_name chargeName,charge_mobile chargeMobile,appid,appsecret,token, access_token accessToken,token_fresh_time tokenFreshTime,"
			+ "create_time createTime,status,backUrl from t_org ")
	List<OrgInfo> getOrgList();

	@Update("update t_org set backUrl=#{backUrl} where id=${id}")
	int updateOrg(OrgInfo org);

}
