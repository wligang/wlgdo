package com.wlgdo.hido.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.wlgdo.hido.common.mongo.MongoGenDao;
import com.wlgdo.hido.domain.ActivityPo;
import com.wlgdo.hido.domain.EssayPo;
import com.wlgdo.hido.service.IActivityService;

/**
 * def:文章业务类 2016年12月10日 wlg
 */
@Service
public class ActivityService extends MongoGenDao<EssayPo> implements IActivityService {

	static final Logger log = LoggerFactory.getLogger(ActivityService.class);

	public static final String ACT_WORDS="actWords";
	
	@Override
	public List<ActivityPo> queryActivityListByid(String actid, boolean isAll) {

		Query query = new Query(Criteria.where("id").is(actid));
		List<ActivityPo> actpo = this.mongoTemplate.find(query, ActivityPo.class);

		return actpo;
	}

	/*
	 * @see
	 * com.crit.service.IActivityService#saveActivityTimeByparams(com.crit.PoJo.
	 * ActivityPo)
	 */
	public int saveActivityTimeByparams(ActivityPo actPo) {
		Query query = new Query(Criteria.where("id").is(actPo.getId()));
		try {
			ActivityPo actpo = this.mongoTemplate.findOne(query, ActivityPo.class);
			this.mongoTemplate.save(actpo);
		} catch (Exception e) {
			log.error("保存活动次数失败{}", e);
			return -1;
		}
		return 0;
	}

	/*
	 * type0:执行次数  1:参与次数 
	 * 
	 */
	@Override
	public int saveActivityTimeByparams(String actid, int type) {
		Query query = new Query(Criteria.where("id").is(actid));
		try {
			ActivityPo actpo = this.mongoTemplate.findOne(query, ActivityPo.class);
			if(actpo==null){
				actpo=new ActivityPo();
				actpo.setId(actid);
				actpo.setLoadtime(0);
				actpo.setActtime(0);
				switch (actid) {
				case "0":
					actpo.setNm("访问页面");
					actpo.setDesc("访问页面的统计");
					break;
				case "1":
					actpo.setNm("手机摇一摇活动");
					actpo.setDesc("手机摇一摇活动");
					break;
				default:
					actpo.setNm("未知的活动");
					actpo.setDesc("未知活动");
					break;
				}

			}
			switch (type) {
			case 0:
				actpo.setLoadtime(actpo.getLoadtime()+1);
				log.info("用户成功参与一次活动,目前:{}",actpo.getLoadtime());
				break;
			case 1:
				actpo.setActtime(actpo.getActtime()+1);
				log.info("用户成功执行了一次活动,目前:{}",actpo.getActtime());
				break;
			}
			this.mongoTemplate.save(actpo);
		} catch (Exception e) {
			log.error("保存活动次数失败{}", e);
			return -1;
		}

		return 0;
	}

	/* 
	 * @see com.crit.service.IActivityService#addWords(java.lang.String, java.lang.String)
	 */
	@Override
	public String[] addWords(String words, String uid) {
		log.info("保存关键词：{},{}",uid,words);
		Query query=new Query();
		if(!"0".equals(uid)){
			query=new Query(Criteria.where("uid").is(uid));
		}
		@SuppressWarnings("rawtypes")
		List<Map> list = this.mongoTemplate.find(query, Map.class, ACT_WORDS);
		HashMap<String, Object> word =null;
		for(String w:words.split("-")){
			word= new HashMap<String,Object>();
			word.put("wd", w);
			word.put("uid", uid);
			list.add(word);
		}
		this.mongoTemplate.save(list, ACT_WORDS);
		
		List<String> wordlis=new ArrayList<>();
		for(Map<?, ?> m:list){
			wordlis.add((String)m.get(""));
		}
		return wordlis.isEmpty()? null:(String[])wordlis.toArray();
	}

	/* 
	 * @see com.crit.service.IActivityService#queryWords(java.lang.String, java.lang.String)
	 */
	@Override
	public String[] queryWords(String words, String uid) {
		log.info("查询关键词uid:{}",uid);
		Query query=new Query();
		if(!"0".equals(uid)){
			query=new Query(Criteria.where("uid").is(uid));
		}
		
		@SuppressWarnings("rawtypes")
		List<Map> list = this.mongoTemplate.find(query, Map.class, ACT_WORDS);
		log.info("查询关键词结果:{}",list);
		List<String> wordlis=new ArrayList<>();
		//组装类型
		for(Map<?, ?> m:list){
			wordlis.add((String)m.get(""));
		}
		System.out.println(list);
		return wordlis.isEmpty()? null:(String[])wordlis.toArray();
	}

}
