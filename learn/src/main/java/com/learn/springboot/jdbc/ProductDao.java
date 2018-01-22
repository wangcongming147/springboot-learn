package com.learn.springboot.jdbc;

import com.learn.springboot.entity.Product;

public interface ProductDao {

	void insert(Product entity);

	void delete(String id);

	Product query(String id);

}
