package com.pb.weixin.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import redis.clients.jedis.Jedis;

//用于jedis测试
public class JedisTest {

	@Test
	public void testJedis() {
	  
		//1.连接redis
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		jedis.auth("123456");  //登录密码
		
	
		//2.操作redis
		//jedis.set("hello", "Hello World");
		String hello = jedis.get("hello");
		System.out.println("hello,"+hello);
		
		
		//3.关闭redis
		jedis.close();	
	}
	
	
	@Test
	public void testList() {
		//1.连接redis
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		jedis.auth("123456");  //登录密码
		
		
//		jedis.lpush("list1", "a","b","c");
//		jedis.lpush("list1","x");
		
		List<String> list1  = jedis.lrange("list1", 0, -1);
		
		for(String s: list1) {
			System.out.println(s);
		}
		
		System.out.println("list1的个数:"+jedis.llen("list1"));
		
		jedis.close();
	}
	
	@Test
	public void testHash() {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		jedis.auth("123456");
		
		jedis.hset("user", "name", "zhangsan");
		jedis.hset("user", "age", "23");
		jedis.hset("user", "weight", "23");
		
		
		Map<String, String> user =  jedis.hgetAll("user");
		System.out.println("user:"+user);
		
		jedis.close();
		
		
	}
	
}
