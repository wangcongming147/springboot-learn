package com.learn.springboot.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.springboot.entity.Product;
import com.learn.springboot.jdbc.ProductDao;
import com.learn.springboot.service.ProductService;
import com.learn.springboot.util.UidUtil;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Override
	public String testJdbc(){
		String id = UidUtil.genIdByParam();
		Product p = new Product(id, "apple 8", "4925.00" , "苹果手机", "320g");
		log.info("------------Product:{}-----------",p);
		productDao.insert(p);
		Product product = productDao.query(id);
		log.info("------------Product:{}-----------",product);
		return product.toString();
	}
}
