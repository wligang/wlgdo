package com.wlgdo.lottery.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.wlgdo.lottery.domain.Prize;

public interface PrizeMapper {

    @Insert("insert into t_prize(id,uid,award_id,create_time,reward_index ,receive_time,status) "
            + "values(#{id},#{uid},#{awardId},#{createTime},${rewardIndex},#{receiveTime},${status})")
    int savePrize(Prize prize);

    @Select("select id,uid,award_id awardId ,create_time createTime,receive_time receiveTime,status ,"
            + "reward_index rewardIndex from t_prize order by reward_index desc")
    List<Prize> listPrize(Prize prize);

    int insertBatchPrize(List<Prize> prizeList);


}
