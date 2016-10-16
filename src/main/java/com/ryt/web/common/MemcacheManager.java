/**
 * 
 */
package com.ryt.web.common;

/**
 * @author xiaoren
 *
 */

import java.util.Date;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.whalin.MemCached.MemCachedClient;

public class MemcacheManager {

	// 获取Client方法
	public static MemCachedClient getClient() {
		return Memcache.getInstance().getClient();
	}

	public static Object get(String key) {
		return getClient().get(key);
	}

	public static boolean set(String key, Object value) {
		
		return getClient().set(key, value);
	}

	public static boolean set(String key, Object value, long time) {
		return getClient().set(key, value, new Date(time));
	}

	public static boolean delete(String key) {
		return getClient().delete(key);
	}

	public static boolean flushAll() {
		return getClient().flushAll();
	}
}
