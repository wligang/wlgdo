package com.wlgdo.hido.service;

import java.util.List;

import com.wlgdo.hido.domain.ActivityPo;

/**
 * 活动服务类
 * @author wlgdo[wlgchun@163.com] 2016年12月31日
 *
 */

public interface IActivityService {
	
	/**
	 * 用户uid查找文章动态
	 * @author wlgdo[wlgchun@163.com] 2016年12月31日 
	 * @param attribute void
	 * @return 
	 */
	List<ActivityPo> queryActivityListByid(String actid,boolean isAll);
	
	/**
	 * 
	 * 保存活动的状态数据
	 * @author wlgdo[wlgchun@163.com] 2017年1月5日 
	 * @param actid
	 * @param type
	 * @return int
	 */
	int saveActivityTimeByparams(String actid,int type);

	/**
	 * 添加活动关键词
	 * @author wlgdo[wlgchun@163.com] 2017年1月9日 
	 * @param words
	 * @param uid
	 * @return int
	 */
	String[] addWords(String words, String uid);

	/**
	 * 查询关键词
	 * @author wlgdo[wlgchun@163.com] 2017年1月9日 
	 * @param words
	 * @param uid
	 * @return List<Map>
	 */
	String [] queryWords(String words, String uid);
	
	
}
