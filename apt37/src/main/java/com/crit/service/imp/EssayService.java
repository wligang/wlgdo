package com.crit.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.crit.common.RandomStrUtils;
import com.crit.common.mongo.MongoGenDao;
import com.crit.domain.CommentPo;
import com.crit.domain.EssayPo;
import com.crit.service.IEssayService;

/**
 * 动态业务类
 * @author wlg 2017年1月7日
 */
@Service
public class EssayService extends MongoGenDao<EssayPo> implements IEssayService {

	static final Logger log = LoggerFactory.getLogger(EssayService.class);

	@Override
	public EssayPo saveEssay(EssayPo esaay) {
		try {
			esaay.setcTime(new Date());
			if (StringUtils.isNotBlank(esaay.getImgurl()) && StringUtils.isNotBlank(esaay.getContext())) {
				esaay.setType(0);// 图文
			} else if (StringUtils.isBlank(esaay.getImgurl())) {
				esaay.setType(1);// 文
			} else if (StringUtils.isNotBlank(esaay.getContext())) {
				esaay.setType(2);// 图
			}
			
			esaay.setcTime(new Date());
			esaay.setId(RandomStrUtils.getRandomSting(8));
			this.mongoTemplate.save(esaay);
		} catch (Exception e) {
			log.error("用户保存文章失败:{}", e);
			return null;
		}

		return esaay;
	}

	@Override
	public List<EssayPo> queryEssayListByid(String uid, boolean isNotopen) {
		Criteria criteria = null;
		List<EssayPo> list = null;
		// 如果是不公开的
		if (!isNotopen) {
			criteria = Criteria.where("uid").is(uid);
		} else {
			criteria = Criteria.where("isOpen").is(0);
		}
		Query query = new Query(criteria);
		Order orders=new Order(Direction.DESC, "cTime");
		query.with(new Sort(orders));  
//		query.with(new Sort(new Order(Direction.DESC,"age")));  
		list = this.mongoTemplate.find(query, EssayPo.class);
		return list;
	}

	@Override
	public List<EssayPo> queryEssayList(EssayPo essay) {
		Query query = new Query();
		return this.mongoTemplate.find(query, EssayPo.class);
	}

	@Override
	public int saveComment(CommentPo comment) {

		this.mongoTemplate.save(comment);
		return 0;
	}

	@Override
	public List<CommentPo> queryCommentList(CommentPo commnet) {
		Query query = new Query();
		return this.mongoTemplate.find(query, CommentPo.class);
	}

	/*
	 * @see com.crit.service.IEssayService#saveEssayZan(java.lang.String,
	 * java.lang.String) retun 1:赞成功 0：已赞 -1 ：赞异常
	 */
	@Override
	public int saveEssayZan(String essayId, String uid) {
		EssayPo t = new EssayPo();
		try {
			t = findObjectById(essayId, t);
			// 校验是否被赞过,被赞过返回0
			if (t != null) {
				if (!t.getLikeList().isEmpty() && t.getLikeList().contains(uid)) {
					log.info("该用户已经赞过该动态数据:{}", t);
					return 0;
				} else {
					t.getLikeList().add(uid);
				}
				t.setZanNum(t.getZanNum() + 1);
				t.getLikeList().add(uid);
				// 保存最新状态
				save(t);
				return t.getZanNum();
			}
		} catch (Exception e) {
			log.error("保存赞信息失败：{},exc:{}", essayId, e);
		}
		return -1;
	}

	@Override
	public int saveComent(String context, String essayId, String uid) {
		try {
			EssayPo essayPo = findObjectById(essayId, new EssayPo());
			if (essayPo != null) {
				if(essayPo.getCommenList()==null){
					essayPo.setCommenList(new ArrayList<>());
				}
				CommentPo commPo = new CommentPo(context, essayId, uid);
				essayPo.getCommenList().add(commPo);
				this.mongoTemplate.save(essayPo);
				return 1;
			}
		} catch (Exception e) {
			log.error("保存评论失败:{}", e);
		}
		return 0;
	}

}
