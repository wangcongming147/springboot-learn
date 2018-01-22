package com.learn.springboot.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.learn.springboot.data.Pagination;
import com.learn.springboot.entity.Order;

@Repository
public class OrderMao {

	@Autowired
    private MongoTemplate mongoTemplate;
	
	public void save(Order order){
		mongoTemplate.save(order);
	}
	
	public Order queryOne(String id){
		Query query = new Query(Criteria.where("id").is(id));
		return mongoTemplate.findOne(query, Order.class);
	}
	
	public Order query(Order order){
		
		Query query = getQuery(order);
		return mongoTemplate.findOne(query, Order.class);
	}
	
	public List<Order> queryAll(Order order){
		Query query = getQuery(order);
		query.with(new Sort(new org.springframework.data.domain.Sort.Order(Direction.DESC,"orderTime")));
		return mongoTemplate.find(query, Order.class);
	}
	
	public void saveAll(List<Order> orders){
		mongoTemplate.insertAll(orders);
	}
	
	/**
	 * 
	 * @Title: queryBypage   
	 * @Description: 
	 * @param pageNo  
	 * @param pageSize  每页显示条数
	 * @param order
	 * @return           
	 * @throws
	 */
	public Pagination<Order> queryBypage(int pageNo,int pageSize,Order order){
		Query query = getQuery(order);
		//查询总条数
		long count = mongoTemplate.count(query, Order.class);
		Pagination<Order> page = new Pagination<Order>(pageNo, pageSize, count);
		query.skip(page.getFirstResult());// skip相当于从那条记录开始  
		query.limit(pageSize);// 从skip开始,取多少条记录  
		List<Order> orders = mongoTemplate.find(query, Order.class);
		page.setDatas(orders); 
		return page;
	}
	
	protected Query getQuery(Order order){
		Query query = new Query();
		if(order == null){
			return query;
		}
		
		if(!StringUtils.isEmpty(order.getId())){
			query.addCriteria(Criteria.where("id").is(order.getId()));
		}
		
		if(order.getOrderTime() != null){
			//gte  大于等于  lte小于等于  gt大于  lt小于
			query.addCriteria(Criteria.where("orderTime").gte(order.getOrderTime().getTime()));
		}
		
		if(!StringUtils.isEmpty(order.getPid())){
			query.addCriteria(Criteria.where("pid").is(order.getPid()));
		}
		
		if(!StringUtils.isEmpty(order.getUid())){
			query.addCriteria(Criteria.where("uid").is(order.getUid()));
		}
		
		if(order.getTotalPrice() != null){
			query.addCriteria(Criteria.where("totalPrice").is(order.getTotalPrice()));
		}
		
		return query;
	}
}
