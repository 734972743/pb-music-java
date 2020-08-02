package com.pb.weixin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.metadata.MethodType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pb.weixin.service.IJedisService;
import com.pb.weixin.service.IMessageService;
import com.pb.weixin.service.IUserService;
import com.pb.weixin.utils.BaseResult;
import com.pb.weixin.vo.Message;
import com.pb.weixin.vo.SongList;
import com.pb.weixin.vo.User;

@RestController
@RequestMapping(value="/message")
public class MessageController {

	@Autowired
	private IMessageService messageService;
	
	
	@Autowired
	private IUserService userService;
	
	
	
	
	
	
	@RequestMapping(value="/getCountBySongId/{songId}",method=RequestMethod.GET)
	public BaseResult<Long> getCountBySongId(@PathVariable("songId") int songId) {  //查询某首音乐的最大的消息数量
		BaseResult<Long> result = new BaseResult<Long>();
		Message message = new Message();
		message.setSongId(songId);
		try {
			Long count =  messageService.getCountBySongId(message);
			result.setData(count);
			result.setCode(200);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setData(null);
			result.setCode(200);
		}
		return result;
	}
	
	@RequestMapping(value="/getMessagesBySongId/{songId}/{curPage}/{pageSize}", method=RequestMethod.GET)
	public BaseResult<List<Message>> getMessagesBySongId(@PathVariable("songId")int songId, @PathVariable("curPage")int curPage , @PathVariable("pageSize")int pageSize){  //查询某首音乐的所有的回复消息
		BaseResult<List<Message>> result = new BaseResult<List<Message>>();
		curPage = curPage -1;
		
		
//		Message message = new Message();
//		message.setSongId(songId);
		
		try {
			
			List<Integer> userIds = new ArrayList<Integer>();
			
			List<Message> messages =  messageService.getMessagesBySongId(songId);
				
			
			for(Message m: messages) {
				userIds.add(m.getUserId());
			}
			
			List<User> users = new ArrayList<User>();
			if(userIds.size() > 0) {
				users = userService.getUsersByIds(userIds);
			}
			
		
			result.setObj(users);
			result.setCode(200);
			result.setData(messages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(500);
		}

		return result;
	}
	
	@RequestMapping(value="/replyMessage",method=RequestMethod.POST)
	public BaseResult<Integer> replyMessage(@RequestBody Message message) {  //回复消息
		BaseResult<Integer> result = new BaseResult<Integer>();
		message.setCreateTime(new Date());
		try {
			messageService.replyMessage(message);
			result.setCode(200);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(500);
		}
		return result;
	}
	
}
