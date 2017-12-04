package com.wlgdo.common.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public abstract class MongoGenDao<T> {

	   @Autowired
	    protected MongoTemplate mongoTemplate;
	    
	    /**
	     * 保存一个对象
	     * @author <a href='http://www.wlgdo.com'>王利刚(wlgchun@163.com)</a> Copy Right wlgdo 2017-01-07 14:25:00
	     * @param t
	     * @return
	     */
	    public void save(T t){
	        this.mongoTemplate.save(t);
	    }    
	    
	    /**
	     * 为属性自动注入bean服务
	     * @author <a href='http://www.wlgdo.com'>王利刚(wlgchun@163.com)</a> Copy Right wlgdo 2017-01-07 14:25:00
	     * @param mongoTemplate
	     */
	    public void setMongoTemplate(MongoTemplate mongoTemplate) {
	        this.mongoTemplate = mongoTemplate;
	    }
	    
	    /**
	     * 查询单个对象 
	     * @author <a href='http://www.wlgdo.com'>王利刚(wlgchun@163.com)</a> Copy Right wlgdo 2017-01-07 14:25:00
	     * @param t bean对象
	     * @param id 主键
	     * @return T
	     */
	    @SuppressWarnings("unchecked")
		public T findObjectById(String id ,T t){
	    	Criteria criteria = Criteria.where("id").is(id);
			t=(T) this.mongoTemplate.findOne(new Query(criteria), t.getClass());
			return t;
	    }
	    
	    
}
