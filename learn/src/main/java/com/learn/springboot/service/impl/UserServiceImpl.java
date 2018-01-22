package com.learn.springboot.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.springboot.jpa.User;
import com.learn.springboot.jpa.UserDao;
import com.learn.springboot.redis.RedisRao;
import com.learn.springboot.service.UserService;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RedisRao redisRao;

	@Override
	public String testJpa(){
		String id = String.valueOf(System.nanoTime());
		User user = new User(id, "小明", "13221114", "小明", 12, "男");
		log.info("-----------user:{}",user);
		userDao.save(user);
		
		User user2 = userDao.find(id);
//		User user2 = userDao.findOne(id);
		log.info("-----------user2:{}",user2);
		
		redisRao.setHash("user12315626", id, user);
		redisRao.expire("user12315626", 10, TimeUnit.HOURS);
		long expire = redisRao.getExpire("user12315626");
		log.info("---------expire time : {} ---------",expire);
		
		User user3 = (User)redisRao.getHashValue("user12315626", id);
		log.info("------------user3 :{}",user3);
		
		return JSONObject.fromObject(user).toString();
	}
	
}
