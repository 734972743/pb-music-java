package com.pb.weixin.service;

public interface IJedisService {

	/**
	 * 设置值
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(String key, String value);
	
	/**
	 * 设置有时间限制的设值
	 * @param key
	 * @param seconds  单位秒
	 * @param value
	 * @return
	 */
	public String setex(String key, int seconds, String value );
	
	
	/**
	 * 根据key来获取value
	 * @param key
	 * @return
	 */
	public String get(String key);
	
	/**
	 * 删除key
	 * @param key
	 * @return
	 */
	public long del(String key);
	
	
	/**
	 * 设置key生存时间
	 * @param key
	 * @param second
	 * @return
	 */
	public long expire(String key, int second);
}
