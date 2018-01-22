package com.learn.springboot.cache;

import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CacheTestService {

	@Cacheable(value="cache",key = "#id")
	public String testCache(String id){
		log.info("=============id:{}============",id);
		return id;
	}
}
