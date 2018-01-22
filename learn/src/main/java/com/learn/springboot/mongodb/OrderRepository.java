package com.learn.springboot.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learn.springboot.entity.Order;

/**
 * 
 * @ClassName:  这个不在实现，后续学习   
 * @Description:TODO 
 * @author: wangcongming
 * @date:   2018年1月1日 下午4:25:03   
 *
 */
public interface OrderRepository extends MongoRepository<Order, String> {

	
}
