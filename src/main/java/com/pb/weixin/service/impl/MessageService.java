package com.pb.weixin.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pb.weixin.dao.IMessageDao;
import com.pb.weixin.service.IJedisService;
import com.pb.weixin.service.IMessageService;
import com.pb.weixin.vo.Message;


@Service
public class MessageService implements IMessageService {
	
	@Autowired
	private IMessageDao messageDao;
	
//	@Autowired
//	private RedisTemplate<Object,Object>  redisTemplate ;
	
	@Autowired
	private IJedisService jedisService;
	
	private static final ObjectMapper mapper = new ObjectMapper();  //这个用来做序列化的
	
	//项目名_模块名_业务名
	private static final String REDIS_MESSAGE_KEY = "pb_music_music_message_" ;  //key取名规则:
	private static final String REDIS_MESSAGE_COUNT_KEY = "pb_music_music_message_count_" ;  //key取名规则:
	
	private static final int REDIS_TIME = 60*60*24*7;  //这个key保存的时间
	
	@Override
	public long getCountBySongId(Message message) {
		long count = 0;
		
		String redis_key =  REDIS_MESSAGE_COUNT_KEY + message.getSongId();  //key值
		
		//从缓存中查询;
		String countStr = jedisService.get(redis_key);
		if(StringUtils.isNotEmpty(countStr)) {
			synchronized (this) {
				if(StringUtils.isNotEmpty(countStr)) {
					count = Long.parseLong(countStr);
				}
			}
		}else {
			Example example = Example.of(message);
			count =  messageDao.count(example);
			jedisService.setex(redis_key, REDIS_TIME, count+"");
			
		}
		
		return count;
	}

	@Override
	public List<Message> getMessagesBySongId(Integer songId) throws JsonProcessingException  {
		
		List<Message> data = new ArrayList<Message>(); 
		String redis_key_message_song_id =  REDIS_MESSAGE_KEY + songId;  //key值
		String dataStr = jedisService.get(redis_key_message_song_id);
		
		if(StringUtils.isNotEmpty(dataStr)) {
			synchronized (this) {
				dataStr =  jedisService.get(redis_key_message_song_id);
				if(StringUtils.isNotEmpty(dataStr)) {
					
					//把字符串转成list对象
					try {
						
						//这样就可以把json字符串转换成想要的List.
						data = mapper.readValue(dataStr, new TypeReference<List<Message>>() {});
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}else {
			Message message = new Message();
			message.setSongId(songId);
			Example example = Example.of(message);
			data = messageDao.findAll(example);
			
			//把数据写到缓存中
			//mapper.writeValueAsString 这个是用来把对象变成json字符串的
			//缓存数据保存7天
			jedisService.setex(redis_key_message_song_id, REDIS_TIME , mapper.writeValueAsString(data));
		}
		
		return data;
	}
	
//	@Override
//	public List<Message> getMessagesBySongId(Integer songId) {
//		return messageDao.getMessagesBySongId(songId);
//	}

	@Override
	public void replyMessage(Message message) {
		// TODO Auto-generated method stub
		Message m = messageDao.saveAndFlush(message);
		
		//修改数据后，就要清除缓存
		String redis_key_message =  REDIS_MESSAGE_KEY + message.getSongId();  //key值
		jedisService.del(redis_key_message);
		
		
		String redis_key_count =  REDIS_MESSAGE_COUNT_KEY + message.getSongId();  //key值
		jedisService.del(redis_key_count);
		
	}
	
	

}
