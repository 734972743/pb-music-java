package com.pb.weixin.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pb.weixin.vo.Message;

public interface IMessageService {

	public long getCountBySongId(Message message);  //查询某首音乐的最大的消息数量
	
//	public Page<Message> getMessagesBySongId(Message message,Pageable pageable) throws JsonProcessingException;  //查询某首音乐的所有的回复消息
	public List<Message> getMessagesBySongId(Integer songId) throws JsonProcessingException;  //查询某首音乐的所有的回复消息
	
	public void replyMessage(Message message);  //回复消息
} 
