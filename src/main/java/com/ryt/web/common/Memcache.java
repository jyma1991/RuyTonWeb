/**
 * 
 */
package com.ryt.web.common;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 * @author xiaoren
 *
 */


public class Memcache {

	protected static Memcache mc = new Memcache();
	
	protected MemCachedClient mcc = new MemCachedClient();
	
	static{
		//服务器列表和其权重
		String[] servers = {"139.196.34.213:12000"};
		//String[] servers = {"192.168.71.195:12000"};
		Integer[] weights = {3};
		
		//获取sock连接池的实例对象
		SockIOPool pool = SockIOPool.getInstance();
		
		//设置服务器信息
		pool.setServers(servers);
		pool.setWeights(weights);
		
		//设置初始连接数、最小最大连接数、最大处理时间
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000*60*60*6);
		
		//设置主线程的睡眠时间
		pool.setMaintSleep(30);
		
		pool.setNagle(false);
		pool.setSocketTO(30);
		pool.setSocketConnectTO(0);
		
		//设置Tcp的参数，连接超时等
		pool.initialize();
		
		//压缩设置，超过制定大小（单位K）的数据都会压缩
		//mcc.setCompressEnable(false);
		//mcc.setCompressThreshold(64*1024);
	}
	
	//保护构造函数，不允许实例化
	protected Memcache(){}
	
	public static Memcache getInstance(){
		return mc;
	}
	
	public MemCachedClient getClient(){
		return mcc;
	}
}
