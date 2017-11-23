package com.zzx.redis.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service
public class RedisService {

	@Autowired
	private StringRedisTemplate redisTemplate;

	public <T> void convertAndSend(String channel, T message) {
		redisTemplate.convertAndSend(channel, JSON.toJSONString(message));
	}

	public void delete(String key) {
		redisTemplate.delete(key);
	}

	public void delete(Collection<String> keys) {
		redisTemplate.delete(keys);
	}

	public Long getExpire(String key) {
		return redisTemplate.getExpire(key);
	}

	public Long getExpire(String key, TimeUnit timeUnit) {
		return redisTemplate.getExpire(key, timeUnit);
	}

	public Boolean expire(String key, long timeout, TimeUnit unit) {
		return redisTemplate.expire(key, timeout, unit);
	}

	public Boolean expireAt(String key, Date date) {
		return redisTemplate.expireAt(key, date);
	}

	public Boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	public <T> void set(String key, T value) {
		redisTemplate.opsForValue().set(key, JSON.toJSONString(value));
	}

	public <T> void set(String key, T value, long expire, TimeUnit unit) {
		redisTemplate.opsForValue().set(key, JSON.toJSONString(value), expire, unit);
	}

	public <T> T get(String key, Class<T> type) {
		return JSON.parseObject(redisTemplate.opsForValue().get(key), type);
	}

	public <T> List<T> getAll(List<String> keys, Class<T> cls) {
		List<String> cacheList = redisTemplate.opsForValue().multiGet(keys);
		if (cacheList == null) {
			return null;
		}
		List<T> list = new ArrayList<T>();
		for (String str : cacheList) {
			list.add(JSON.parseObject(str, cls));
		}
		return list;
	}

	public <T> long lpush(String key, T value) {
		return redisTemplate.opsForList().leftPush(key, JSON.toJSONString(value));
	}

	public <T> long rpush(String key, T value) {
		return redisTemplate.opsForList().rightPush(key, JSON.toJSONString(value));
	}

	public <T> long mrpush(String key, List<T> values) {
		if (values == null || values.isEmpty()) {
			return 0;
		}
		List<String> list = new ArrayList<String>();
		for (T o : values) {
			list.add(JSON.toJSONString(o));
		}
		return redisTemplate.opsForList().rightPushAll(key, list);
	}

	public <T> T lpop(String key, Class<T> type) {
		return JSON.parseObject(redisTemplate.opsForList().leftPop(key), type);
	}

	public <T> T rpop(String key, Class<T> type) {
		return JSON.parseObject(redisTemplate.opsForList().rightPop(key), type);
	}

	public <T> void hset(String key, String hashKey, T value) {
		redisTemplate.opsForHash().put(key, hashKey, JSON.toJSONString(value));
	}

	public <T> T hget(String key, String hashKey, Class<T> type) {
		Object obj = redisTemplate.opsForHash().get(key, hashKey);
		if (obj instanceof String) {
			return JSON.parseObject((String) obj, type);
		}
		return null;
	}

	public <T> void mhset(String key, Map<String, T> map) {
		if (map == null || map.isEmpty()) {
			return;
		}
		Map<String, String> newMap = new HashMap<String, String>();
		for (Entry<String, T> entry : map.entrySet()) {
			newMap.put(entry.getKey(), JSON.toJSONString(entry.getValue()));
		}
		redisTemplate.opsForHash().putAll(key, newMap);
	}

	public <T> Map<String, T> mhget(String key, Class<T> type) {
		Map<Object, Object> cache = redisTemplate.opsForHash().entries(key);
		if (cache == null) {
			return null;
		}
		Map<String, T> map = new HashMap<String, T>();
		for (Entry<Object, Object> i : cache.entrySet()) {
			map.put(String.valueOf(i.getKey()), JSON.parseObject((String) i.getValue(), type));
		}
		return map;
	}

	public Long hsize(String key) {
		return redisTemplate.opsForHash().size(key);
	}
}
