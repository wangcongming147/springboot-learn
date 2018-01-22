package com.learn.springboot.util;

import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

public class UidUtil {

	/**
	 * 
	 * @Title: genId   
	 * @Description: 根据时间生成id
	 * @return           
	 * @throws
	 */
	public static String genIdByDate(){
		return String.valueOf(System.nanoTime());
	}
	
	/**
	 * 
	 * @Title: genIdByUUId   
	 * @Description: UUId 
	 * @return           
	 * @throws
	 */
	public static String genIdByUUId(){
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
	
	/**
	 * 
	 * @Title: genIdByUUId2   
	 * @Description: 利用UUID生成id，去除  "-"
	 * @return           
	 * @throws
	 */
	public static String genIdByUUId2(){
		return genIdByUUId().replace("-", "");
	}
	
	/**
	 * 
	 * @Title: genIdByParam   
	 * @Description: 根据传入参数加上随机数再加上当前时间纳秒数进行md5加密
	 * @param parm
	 * @return           
	 * @throws
	 */
	public static String genIdByParam(String ...parm){
		String p = "";
		p += new Random().nextInt(12456488);
		p += genIdByDate();
		if(parm.length == 0){
			return StringUtils.defaultIfEmpty(MD5.md5(p),genIdByDate());
		}
		
		for (int i = 0; i < parm.length; i++) {
			p += parm[i];
		}
		return StringUtils.defaultIfEmpty(MD5.md5(p),genIdByDate());
	}
}
