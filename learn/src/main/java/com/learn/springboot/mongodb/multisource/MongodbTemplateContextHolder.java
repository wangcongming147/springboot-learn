package com.learn.springboot.mongodb.multisource;
/**
 * @title: mongodbTemplateContextHolder
 * @company: 北京云知声信息技术有限公司
 * @author: lizehao
 * @date: 2016年10月18日
 */
public class MongodbTemplateContextHolder{

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setMongoDBTemplate(String mongodbTemplateType) {
        contextHolder.set(mongodbTemplateType);
    }

    public static String getMongoDBTemplate() {
        return contextHolder.get();
    }

    public static void clearMongoDBTemplate() {
        contextHolder.remove();
    }
}