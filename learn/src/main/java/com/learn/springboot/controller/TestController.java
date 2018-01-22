package com.learn.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.springboot.cache.CacheTestService;
import com.learn.springboot.service.OrderService;
import com.learn.springboot.service.ProductService;

@RestController
@RequestMapping
public class TestController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CacheTestService cacheTestService;
	
	@RequestMapping("/testJdbc")
	public String testJdbc(){
		return productService.testJdbc();
	}
	
	@RequestMapping("/testMonTemp")
	public String testMonTemp(){
		return orderService.testMonTemp();
	}
	
	@RequestMapping("/testCache")
	public String testCache(String id){
		return cacheTestService.testCache(id);
	}
}
