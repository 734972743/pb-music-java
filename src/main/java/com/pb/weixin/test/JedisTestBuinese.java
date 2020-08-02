package com.pb.weixin.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

public class JedisTestBuinese {
	
	
	
	

	public static void main(String[] args) {
		MyThread mt1 = new MyThread("初级用户",10);
		MyThread mt2 = new MyThread("高级用户",30);
		
		mt1.start();
		mt2.start();
	}
	
	
	//这个是redis业务逻辑
	public void business(String id, int num) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		jedis.auth("123456");
	
		try {
			String value =  jedis.get("business-id:"+id);
			if(value == null) {
				//如果不存在
				jedis.setex("business-id:"+id, 20, Long.MAX_VALUE-num+"");	
			}else {
				//如果存在
				Long val = jedis.incr("business-id:"+id);
				System.out.println("用户:"+id+",已使用"+(num-(Long.MAX_VALUE-val))+"次了");
			}
		}catch(JedisDataException e) {
			System.out.println(id+",你的使用此时已用完，请充值");
		}finally {
			jedis.close();
		}
		
	}
	
}



class MyThread extends Thread{
	
	private String id ;
	private int num;
	
	public MyThread(String id, int num) {
		this.id = id;
		this.num = num;
	}
	
	
	JedisTestBuinese business = new JedisTestBuinese();
	
	public void run() {
		while(true) {
			business.business(id, num);
			//System.out.println("业务正在运行");
			
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	
}
