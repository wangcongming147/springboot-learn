package com.learn.guava;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import lombok.extern.log4j.Log4j;

@Log4j
public class GuavaTester {
	public static void main(String[] args) throws ExecutionException {
		//缓存接口这里是LoadingCache，LoadingCache在缓存项不存在时可以自动加载缓存
        LoadingCache<String,String> studentCache
                //CacheBuilder的构造函数是私有的，只能通过其静态方法newBuilder()来获得CacheBuilder的实例
                = CacheBuilder.newBuilder()
                //设置并发级别为8，并发级别是指可以同时写缓存的线程数
                .concurrencyLevel(8)
                //设置写缓存后8秒钟过期
                .expireAfterWrite(8, TimeUnit.SECONDS)
                //设置缓存容器的初始容量为10
                .initialCapacity(10)
                //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
                .maximumSize(100)
                //设置要统计缓存的命中率
                .recordStats()
                //设置缓存的移除通知
                .removalListener(new RemovalListener<Object, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                    	log.info(notification.getKey() + " was removed, cause is " + notification.getCause());
                    }
                })
                //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
                .build(
                        new CacheLoader<String, String>() {
                            @Override
                            public String load(String key) throws Exception {
                                log.info("获取到key：" + key);
                                
                                //通过key查询redis缓存获取
                                
                                //通过key查询数据库获取
                                
                                
                                return "ssss";
                            }
                        }
                );
        
        String value = studentCache.get("12331546");//获取缓存值
//        studentCache.getAll()
        studentCache.invalidate("12331546");//移除缓存
        studentCache.invalidateAll();
//        studentCache.
//        使用Cache.invalidate(key)单个移除;
//        使用Cache.invalidteAll(keys)批量移除；
//        使用Cache.invalidateAll()移除全部。
        
        
        
        
	}
}