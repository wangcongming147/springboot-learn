package com.learn.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sf.json.JSONObject;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	private String id;
	private String name;
	private String price;
	private String description;
	private String weight;
	
	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}
