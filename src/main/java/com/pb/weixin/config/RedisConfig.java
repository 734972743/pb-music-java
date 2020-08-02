package com.pb.weixin.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 这是redis配置文件
 * @author ASUS
 *
 */
@Configuration   //这是springboot里 标注配置文件
@PropertySource("classpath:redis.properties")   //表明这是引用哪个配置文件的
public class RedisConfig {
	
	/** 非切片连接池 */
    private JedisPool jedisPool;
	
	 /** 切片连接池 */
    private ShardedJedisPool shardedJedisPool;
	
	private Logger logger = LoggerFactory.getLogger(RedisConfig.class); 

	@Value("${spring.redis.host}")
	private String host;
	
	@Value("${spring.redis.port}")
	private int port;
	
	@Value("${spring.redis.password}")
	private String password;
	
	@Value("${spring.redis.timeout}")
	private int timeout;
	
	@Value("${spring.redis.jedis.pool.max-idle}")
	private int maxIdle;
	
	@Value("${spring.redis.jedis.pool.max-wait}")
	private int maxWaitMillis;
	
	@Value("${spring.redis.block-when-exhausted}")
	private boolean blockWhenExhausted;
	

	
	
	
	/**
     * 初始化非切片连接池
     */
	public JedisPool jedisPoolFactory()throws Exception {
		logger.info("jedisPool注入成功");
		logger.info("reids地址: host"+host+",port"+port);
		
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		
		
		//链接数耗尽时是否阻塞， false会异常,tue会一直阻塞到超时为止，默认为true
		jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
		
		//是否启用pool的jmx管理功能，默认为true;
		jedisPoolConfig.setJmxEnabled(true);
		
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
		
		return jedisPool;		
		
		
	}
	
	  /**
     * 初始化切片连接池
     */
    @Bean(name="shardedJedisPool")    //bean 中的name 值，我们在调用的时候可以通过@autowired来获取这个ShardedJedisPool对象
    public ShardedJedisPool getShardedJedisPool() {
        // 池基本配置
    	JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		
		
		//链接数耗尽时是否阻塞， false会异常,tue会一直阻塞到超时为止，默认为true
		jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
		
		//是否启用pool的jmx管理功能，默认为true;
		jedisPoolConfig.setJmxEnabled(true);
		
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);

        ArrayList<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
        
        
        //添加分片redis
        JedisShardInfo shard1 = new JedisShardInfo(host,port);
        shard1.setPassword(password);
		list.add(shard1);
		
//		JedisShardInfo shard2 = new JedisShardInfo("49.233.162.66","6379");
//		shard2.setPassword("pb123456");
//		list.add(shard2);

        shardedJedisPool = new ShardedJedisPool(jedisPoolConfig, list);
        return shardedJedisPool;
    }
    
    
    @Bean(name="jedisPool")
    public JedisPool getSingleJedisPool() {
        if (jedisPool == null) {
            synchronized(RedisConfig.class) {
                if (jedisPool == null) {
                    try {
						return jedisPoolFactory();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }
        }
        return jedisPool;
    }
    
    
    @Bean(name="jedis")
    public Jedis getJedis() {
        return getSingleJedisPool().getResource();
    }

    @Bean(name="shardedJedis")
    public ShardedJedis getShardedJedis() {
        return shardedJedisPool.getResource();
    }
	

	
	
	
}
