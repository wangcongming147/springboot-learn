package com.learn.springboot.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.springboot.data.Pagination;
import com.learn.springboot.entity.Order;
import com.learn.springboot.mongodb.OrderMao;
import com.learn.springboot.service.OrderService;
import com.learn.springboot.util.UidUtil;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMao orderMao;
	
	@Override
	public String testMonTemp(){
		log.info("----------------");
		String id = UidUtil.genIdByParam();
		Order order = new Order(id, new Date(), "12448458", "21ewq3232", 1100.2, 0);
		orderMao.save(order);
		log.info("-----------order:{}-------",order);
		Order entity = new Order();
		entity.setId(id);
		entity.setUid("12448458");
		Order o2 = orderMao.query(entity);
		log.info("================order:{}===============",o2);
		log.info("=============批量插入order============");
		Order order1 = new Order(UidUtil.genIdByParam("qww"), new Date(), "12448458", "21ewq3232", 1100.2, 0);
		Order order2 = new Order(UidUtil.genIdByParam("123"), new Date(), "12448459", "21ewq3232", 1100.2, 0);
		Order order3 = new Order(UidUtil.genIdByParam("113"), new Date(), "12448460", "21ewq3232", 1100.2, 0);
		Order order4 = new Order(UidUtil.genIdByParam("qww1"), new Date(), "12448458", "21ewq3232", 1100.2, 0);
		Order order5 = new Order(UidUtil.genIdByParam("1231"), new Date(), "12448459", "21ewq3232", 1100.2, 0);
		Order order6 = new Order(UidUtil.genIdByParam("1131"), new Date(), "12448460", "21ewq3232", 1100.2, 0);
		Order order7 = new Order(UidUtil.genIdByParam("qww2"), new Date(), "12448458", "21ewq3232", 1100.2, 0);
		Order order8 = new Order(UidUtil.genIdByParam("1232"), new Date(), "12448459", "21ewq3232", 1100.2, 0);
		Order order9 = new Order(UidUtil.genIdByParam("1132"), new Date(), "12448460", "21ewq3232", 1100.2, 0);
		List<Order> orders = Arrays.asList(order1,order2,order3,order4,order5,order6,order7,order8,order9);
		log.info("=============orders:{}============",orders);
		orderMao.saveAll(orders);
		log.info("=============批量插入order结束============");
		log.info("=============分页查询============");
		Order o3 = new Order();
		o3.setPid("21ewq3232");
		Pagination<Order> orders1 = orderMao.queryBypage(1, 2, o3);
		log.info("==========第一页：{}=========",orders1);
		Pagination<Order> orders2 = orderMao.queryBypage(2, 2, o3);
		log.info("==========第2页：{}=========",orders2);
		Pagination<Order> orders3 = orderMao.queryBypage(3, 2, o3);
		log.info("==========第3页：{}=========",orders3);
		List<Order> all = orderMao.queryAll(null);
		log.info("==========倒叙查询全部数据all：{}=========",all);
		
		return o2.toString();
	}
}
