package com.learn.springboot.mongodb.multisource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @title: 多数据源动态切换
 * @company: 北京云知声信息技术有限公司
 * @author: lizehao
 * @date: 2016年8月25日
 */
//@Aspect
//@Component
public class DynamicMongoDBDataSourceAspect {
//    private static final String PREFIX = ".dao.mongo.";
//
//    @Pointcut("execution(* com.unisound.iot.busi.web.dao.mongo..*.*(..))")
//    private void daoMethod() {
//        // do nothing
//    }
//
//    @Before("daoMethod()")
//    public void before(JoinPoint joinPoint) {
//        Signature signature = joinPoint.getSignature();
//        for (MongoDBDataSource mongoDBDataSource : MongoDBDataSource.values()) {
//            if (signature.getDeclaringTypeName().indexOf(PREFIX + mongoDBDataSource.getValue()) > -1) {
//                JdbcTemplateContextHolder.setJdbcTemplate(mongoDBDataSource.getValue());
//                break;
//            }
//        }
//    }
}