package com.learn.springboot.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRao {
	
	@Autowired
	RedisTemplate<Object, Object> redisTemplate;

	/**
	 * stringRedisTemplate 和 ValueOperations类不是继承关系，也不是实现类，为什么可以将stringRedisTemplate注入进来？
	 * 查看org.springframework.beans.factory.support.AbstractBeanFactory 的doGetBean()方法可以看到 
	 * //Check if required type matches the type of the actual bean instance.
	 * 如果你要实例化的对象和你的引用对象并不是同一种类型，那么spring就会进行转换。
	 * 
	 * spring 会调用ValueOperationsEditor进行转换创建对象
	 * 
	 */
	@Resource(name = "stringRedisTemplate")
	ValueOperations<String, String> valOpsStr;

	@Resource(name = "redisTemplate")
	ValueOperations<String, Object> valOps;

	@Resource(name = "redisTemplate")
	SetOperations<String, Object> setOps;

	@Resource(name = "redisTemplate")
	ZSetOperations<String, Object> zSetOps;

	@Resource(name = "redisTemplate")
	ListOperations<String, Object> listOps;

	@Resource(name = "redisTemplate")
	HashOperations<String, Object, Object> hMapOps;
	
	public String getString(String key){
		return valOpsStr.get(key);
	}
	
	public void setString(String key,String value){
		valOpsStr.set(key, value);
	}
	
	public Object getObjectValue(String key){
		return valOps.get(key);
	}
	
	public void setObjectValue(String key,Object value){
		valOps.set(key, value);
	}
	
	public boolean expire(String key,long timeout,TimeUnit unit){
		return redisTemplate.expire(key, timeout, unit);
	}
	
	public long getExpire(String key){
		return redisTemplate.getExpire(key);
	}
	
	public long getExpire(String key, TimeUnit timeUnit) {
		return redisTemplate.getExpire(key, timeUnit);
	}
	
	public Boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}
	
	public Set<Object> getKeys(String pattern) {
		return redisTemplate.keys(pattern);
	}
	
	public void delete(String key) {
		redisTemplate.delete(key);
	}
	
	/**
	 * 获取自增值
	 * @param key
	 * @return
	 */
	public int getIncrValue(final String key) {

		return redisTemplate.execute(new RedisCallback<Integer>() {
			@Override
			public Integer doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] rowkey = serializer.serialize(key);
				byte[] rowval = connection.get(rowkey);
				try {
					String val = serializer.deserialize(rowval);
					return Integer.parseInt(val);
				} catch (Exception e) {
					return 0;
				}
			}
		});
	}
	
	/**
	 * 
	 * @Title: getHash   
	 * @Description: 获取整个hash
	 * @param key
	 * @return      
	 * Map<Object,Object>      
	 * @throws
	 */
	public Map<Object, Object> getHash(String key) {
		return hMapOps.entries(key);
	}
	
	/**
	 * 
	 * @Title: getHashValue   
	 * @Description: 获取hash中存储的某一个值
	 * @param key
	 * @param mapKey
	 * @return      
	 * Object      
	 * @throws
	 */
	public Object getHashValue(String key, Object mapKey) {
		return hMapOps.get(key, mapKey);
	}
	
	/**
	 * 
	 * @Title: setHash   
	 * @Description: 将map集合存入到hash中
	 * @param key
	 * @param value      
	 * void      
	 * @throws
	 */
	public void setHash(String key, Map<Object, Object> value) {
		hMapOps.putAll(key, value);
	}
	
	public void setHash(String key,Object hashKey ,Object value) {
		hMapOps.put(key, hashKey, value);
	}
	
	/**
	 * 
	 * @Title: setHashIncrement   
	 * @Description: 将long值存入到hash中，且能做相应的增长
	 * @param key hash key
	 * @param hashKey hash集合的key
	 * @param counts 自增量
	 * @return  long       
	 * @throws
	 */
	public long setHashIncrement(String key, String hashKey, long counts) {
		return hMapOps.increment(key, hashKey, counts);
	}
	
	public void addSet(String key, Object... values) {
		setOps.add(key, values);
	}
	
	public void addSet(String key, Set<Object> values) {
		setOps.add(key, values.toArray());
	}
	
	public Set<Object> getSet(String key) {
		return setOps.members(key);
	}
	
	public long setSize(String key) {
		return setOps.size(key);
	}
	
	/**
	 * 
	 * @Title: getIntersect   
	 * @Description: 取两个key对应value的交集
	 * @param key1
	 * @param key2
	 * @return      
	 * Set<Object>      
	 * @throws
	 */
	public Set<Object> getIntersect(String key1, String key2) {
		return setOps.intersect(key1, key2);
	}
	
	/**
	 * 
	 * @Title: zAddZsetElement   
	 * @Description: 存储zset
	 * @param key
	 * @param value
	 * @param score
	 * @return      
	 * boolean      
	 * @throws
	 */
	public boolean zAddZsetElement(String key, Object value, long score) {
		return zSetOps.add(key, value, score);
	}

	/**
	 * 
	 * @Title: getZsetScore   
	 * @Description: 获取zset中某个元素的score
	 * @param key
	 * @param zSetKey
	 * @return      
	 * long      
	 * @throws
	 */
	public long getZsetScore(String key, Object zSetKey) {
		return zSetOps.score(key, zSetKey).longValue();
	}

	/**
	 * 
	 * @Title: remZsetElement   
	 * @Description:  移除zSet中某个元素
	 * @param key
	 * @param zSetKeys
	 * @return      
	 * Long      
	 * @throws
	 */
	public Long remZsetElement(String key, Object... zSetKeys) {
		return zSetOps.remove(key, zSetKeys);
	}
	
	public long getListSize(String key) {
		return listOps.size(key);
	}

	public void addList(String key, List<Object> values) {
		if (values == null || values.size() == 0) {
			System.out.println("存数据的value不能为空");
			return;
		}

		listOps.rightPushAll(key, values);
	}
	
	/**
	 * 
	 * @Title: getListValue   
	 * @Description: 分页获取list集合数据
	 * @param key 
	 * @param page 页数
	 * @param pagesize 条数
	 * @return      
	 * List<Object>      
	 * @throws
	 */
	public List<Object> getListValue(String key, int page, int pagesize) {
		int start = (page - 1) * pagesize;
		int end = page * pagesize - 1;
		long total = listOps.size(key);
		if (end > total) {
			end = (int) total;
		}
		return listOps.range(key, start, end);
	}
	
	/**
	 * 
	 * @Title: getListAll   
	 * @Description: 取出list全部数据
	 * @param key
	 * @return      
	 * List<Object>      
	 * @throws
	 */
	public List<Object> getListAll(String key) {
		return listOps.range(key, 0, listOps.size(key) - 1);
	}
}
