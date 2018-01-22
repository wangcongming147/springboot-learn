package com.learn.springboot.mongodb.multisource;
/**
 * @title: mongoDB数据源类型
 * @company: 北京云知声信息技术有限公司
 * @author: lizehao
 * @date: 2016年10月18日
 */
public enum MongoDBDataSource {

    USER_CENTER("uc"), // 用户中心数据
    DEVICE_CENTER("device"); // 设备中心数据

    private final String value;

    // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
    private MongoDBDataSource(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}