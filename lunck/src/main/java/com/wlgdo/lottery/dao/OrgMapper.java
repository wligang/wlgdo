package com.wlgdo.lottery.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.wlgdo.lottery.domain.OrgInfo;

public interface OrgMapper {

    /*   `id` int(11) NOT NULL,
       `org_name` varchar(45) DEFAULT NULL,
       `charge_name` varchar(45) DEFAULT NULL COMMENT '负责人名称',
       `charge_mobille` varchar(11) NOT NULL COMMENT '机构联系电话',
       `appid` varchar(45) DEFAULT NULL,
       `appsecret` varchar(45) DEFAULT NULL,
       `mp_token` varchar(45) DEFAULT NULL COMMENT '公众号介入token',
       `access_token` varchar(128) DEFAULT NULL COMMENT '公众号访问token',
       `token_fresh_time` datetime DEFAULT NULL,
       `create_time` datetime DEFAULT NULL,
       `status` int(11) DEFAULT NULL,
       PRIMARY KEY (`id`)*/

    @Select("select id,org_name orgName,charge_name chargeName,charge_mobille chargeMobille,appid,appsecret,token,"
            + "access_token accessToken,token_fresh_time tokenFreshTime,create_time createTime,status"
            + " from t_org where id=${orgId}")
    OrgInfo getOrgInfoById(Integer org);

    @Insert("insert into t_org(org_name,charge_name ,charge_mobille ,appid,appsecret,token,access_token ,token_fresh_time ,"
            + "create_time ,status )"
            + "values(#{orgName},#{chargeName},#{chargeMobille},#{appid},#{appsecret},#{token},#{accessToken},"
            + "#{tokenFreshTime},#{createTime},#{status})")
    int insertOrgInfo(OrgInfo org);

}
