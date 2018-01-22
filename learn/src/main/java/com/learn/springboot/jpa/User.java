package com.learn.springboot.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sf.json.JSONObject;

/**
 * 
 * @ClassName:  User   
 * @Description: jpa demo 实体类  该实体类应注意使用注解@Entity标示
 * @author: wangcongming
 * @date:   2017年12月29日 上午10:30:36   
 *
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 2584373010713524551L;
	/**
	 * 主键
	 */
	@Id
	private String id;
	private String username;
	private String password;
	
	/**
	 * 数据库表中的字段和实体类中的属性映射规则：
	 * 1. 数据库中表字段命名为user_name  则默认对应实体类中的userName属性 ，无法对应到username中
	 * 2. 如果不使用默认映射规则，则可以使用注解@Column(name = "")来进行指定映射，只需将表中字段名填入注解的name中
	 * 
	 */
	@Column(name = "nickname")
	private String name;
	private Integer age;
	private String sex;
	
	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}
