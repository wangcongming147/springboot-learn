package com.learn.springboot.mongodb.multisource;

import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @title: MongoDBJdbcTemplate
 * @company: 北京云知声信息技术有限公司
 * @author: lizehao
 * @date: 2016年10月18日
 */
public class MongoDBTemplate extends AbstractMongoDBRoutingMongoTemplate{

    public MongoDBTemplate() {
    }

    public MongoTemplate getMongoTemplate() {
        return determineMongoTemplate();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return MongodbTemplateContextHolder.getMongoDBTemplate();
    }

}