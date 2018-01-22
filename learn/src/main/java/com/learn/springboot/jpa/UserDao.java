package com.learn.springboot.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, String>{

	/**
	 * 
	 * @Title: find   
	 * @Description: 注意此处 的@Query中的语句不是sql语句，查表时，不应该写表名，而是具体实体类的名字
	 * @param id
	 * @return      
	 * User      
	 * @throws
	 */
	@Query("SELECT u FROM User u WHERE u.id=:id")
	User find(@Param("id") String id);
}
