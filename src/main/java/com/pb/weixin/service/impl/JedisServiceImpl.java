package com.pb.weixin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pb.weixin.service.IJedisService;
import com.pb.weixin.utils.Function;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class JedisServiceImpl implements IJedisService {

	@Autowired(required = false)  // required = false 表示 shardedJedisPool 这个对象有就注入，没有就忽略
	private ShardedJedisPool shardedJedisPool;
	
	
	//<T> T  这是说返回值类型时T，前面又给它加了泛型T
	private <T> T execute(Function<T,ShardedJedis> fun) {
		ShardedJedis shardedJedis = null;
		try {
			//利用jedis分片对象来操作jedis存放数据
			shardedJedis = shardedJedisPool.getResource();
			return fun.callbakck(shardedJedis);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(shardedJedis != null) {
				//将jedis分片对象放回到jedis集群连接池中
				shardedJedis.close();
			}
		}
		
		//关闭连接池
		shardedJedisPool.close();
		return null;
	}
	
	
	@Override
	public String setex(String key, int seconds, String value) {
		// TODO Auto-generated method stub
		return this.execute(new Function<String,ShardedJedis>(){

			@Override
			public String callbakck(ShardedJedis e) {
				// TODO Auto-generated method stub
				return e.setex(key, seconds, value);
			}
			
		});
	}

	
	@Override
	public String set(final String key, final String value) {
		// TODO Auto-generated method stub
		
		//下面是接口匿名实现类
		return this.execute(new Function<String,ShardedJedis>(){

			@Override
			public String callbakck(ShardedJedis shardedJedis) {
				// TODO Auto-generated method stub
				return shardedJedis.getSet(key, value);
			}});
	}

	@Override
	public String get(final String key) {
		// TODO Auto-generated method stub
		return this.execute(new Function<String,ShardedJedis>(){

			@Override
			public String callbakck(ShardedJedis e) {
				// TODO Auto-generated method stub
				return e.get(key);
			}
			
		});
	}

	@Override
	public long del(final String key) {
		// TODO Auto-generated method stub
		return this.execute(new Function<Long,ShardedJedis>(){

			@Override
			public Long callbakck(ShardedJedis e) {
				// TODO Auto-generated method stub
				return e.del(key);
			}
			
		});
	}

	@Override
	public long expire(final String key, final int second) {
		// TODO Auto-generated method stub
		return this.execute(new Function<Long,ShardedJedis>(){

			@Override
			public Long callbakck(ShardedJedis e) {
				// TODO Auto-generated method stub
				return e.expire(key, second);
			}
			
		});
	}


	

}
