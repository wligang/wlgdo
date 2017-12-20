package com.wlgdo.hido.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

	static final Logger log = LoggerFactory.getLogger(AuthorService.class);

	@Override
	public List<Map<String, Object>> queryData(String sql) {
		return new ArrayList<>();
	}

	/**
	 * 鏌ヨ鐢ㄦ埛淇℃伅
	 */
	@Override
	public UserPo findUser(UserPo user) {
		Criteria criteria = Criteria.where("accname").is(user.getAccname()).and("password").is(user.getPassword());
		// criteria.where("password").is(user.getPassword());
		user = this.mongoTemplate.findOne(new Query(criteria), UserPo.class);
		log.info("鐢ㄦ埛鐧婚檰鎴愬姛:{}", user);
		return user;
	}

	@Override
	public boolean saveSuggest(String suggest, String connect) {
		try {
			SuggestPo sug = new SuggestPo();
			sug.setCtime(new Date());
			sug.setUtime(new Date());
			sug.setSuggest(suggest);
			sug.setAccConnect("鏆傛棤");
			sug.setActName("鏆傛棤");
			this.mongoTemplate.save(sug, "suggests");
			log.info("淇濆瓨寤鸿瀹屾瘯{}", sug);
			return true;
		} catch (Exception e) {
			log.info("淇濆瓨寤鸿涓�鍦�-exception{}", e);
		}
		return false;
	}

	@Override
	public UserPo saveUserInfo(UserPo user) {
		// 娣诲姞娉ㄥ唽淇℃伅
		log.info("寮�濮嬪啓鍏ongdb");
		try {
			user.setUid(RandomStrUtils.getRandomSting(16));
			FileUtilz.Base64ToImg(user.getUrl(), "header/" + user.getUid());
			log.info("鍥剧墖淇濆瓨姝ｅ父锛屽皢鐢ㄦ埛淇濆瓨");
			user.setUrl("/header/" + user.getUid());
			this.mongoTemplate.save(user);
		} catch (Exception e) {
			log.error("娉ㄥ唽澶辫触{}", e);
			return null;
		}
		return user;
	}

	/**
	 * 
	 * 妫�鏌ヨ鐢ㄦ埛鏄惁瀛樺湪
	 * 
	 * @author wlgdo[wlgchun@163.com] 2017骞�1鏈�1鏃�
	 * @param key
	 * @param value
	 * @return boolean
	 */
	public boolean checkUserInfo(String key, Object value) {
		Query query = new Query(Criteria.where(key).is(value));
		List<UserPo> list = mongoTemplate.find(query, UserPo.class);
		if (list != null && list.size() > 0) {// 瀛樺湪
			return false;
		}
		return true;
	}

	/**
	 * 妫�鏌ョ敤鎴峰垪琛�
	 * 
	 * @author wlgdo[wlgchun@163.com] 2017骞�1鏈�1鏃�
	 * @param key
	 * @param value
	 * @return boolean
	 */
	@Override
	public List<UserPo> findUserList(String uid, boolean b) {
		Query query = new Query(Criteria.where("uid").exists(true));
		List<UserPo> lsit = mongoTemplate.find(query, UserPo.class);
		log.info("鑾峰彇鐢ㄦ埛鍒楄〃鏄細{}", lsit);
		return lsit;
	}

}
