package com.learn.springboot.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sf.json.JSONObject;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @ClassName:  Order   
 * @Description: mongodb实体类 需要加注解@Document 其中collection属性可以指定存入的集合
 * @author: wangcongming
 * @date:   2017年12月29日 下午10:08:54   
 *
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="order")
public class Order implements Serializable{

	private static final long serialVersionUID = -1488983390037587028L;
	
	private String id;
	private Date orderTime;
	private String uid;
	private String pid;
	private Double totalPrice;
	private Integer state;
	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}
