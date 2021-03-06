package com.wlgdo.hido.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.wlgdo.common.utils.FileUtilz;
import com.wlgdo.hido.common.RandomStrUtils;
import com.wlgdo.hido.common.mongo.MongoGenDao;
import com.wlgdo.hido.domain.SuggestPo;
import com.wlgdo.hido.domain.UserPo;
import com.wlgdo.hido.service.IAuthorService;

@Service
public class AuthorService extends MongoGenDao<UserPo> implements IAuthorService {

	Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public List<Map<String, Object>> queryData(String sql) {
		return new ArrayList<>();
	}

	@Override
	public UserPo findUser(UserPo user) {
		Criteria criteria = Criteria.where("accname").is(user.getAccname()).and("password").is(user.getPassword());
		// criteria.where("password").is(user.getPassword());
		user = this.mongoTemplate.findOne(new Query(criteria), UserPo.class);
		return user;
	}

	@Override
	public boolean saveSuggest(String suggest, String connect) {
		try {
			SuggestPo sug = new SuggestPo();
			sug.setCtime(new Date());
			sug.setUtime(new Date());
			sug.setSuggest(suggest);
			sug.setAccConnect(connect);
			this.mongoTemplate.save(sug, "suggests");
			return true;
		} catch (Exception e) {
			log.error("保存建議出异常了{}-exception{}", suggest, e);
		}
		return false;
	}

	@Override
	public UserPo saveUserInfo(UserPo user) {
		log.info("新用戶注冊：{}", user);
		try {
			user.setUid(RandomStrUtils.getRandomSting(16));
			if(StringUtils.isNotBlank(user.getUrl())) {
				FileUtilz.Base64ToImg(user.getUrl(), "header/" + user.getUid());
			}
			this.mongoTemplate.save(user);
		} catch (Exception e) {
			log.error("新用戶注冊异常了：{}，{}", user, e);
			return null;
		}
		return user;
	}


	public boolean checkUserInfo(String key, Object value) {
		Query query = new Query(Criteria.where(key).is(value));
		List<UserPo> list = mongoTemplate.find(query, UserPo.class);
		if (list != null && list.size() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 查找用户列表
	 * 
	 * @author wlgdo[wlgchun@163.com]
	 * @param key
	 * @param value
	 * @return boolean
	 */
	@Override
	public List<UserPo> findUserList(String uid, boolean b) {
		Query query = new Query(Criteria.where("uid").exists(true));
		List<UserPo> lsit = mongoTemplate.find(query, UserPo.class);
		log.info("查找用户列表{}", lsit);
		return lsit;
	}

}
