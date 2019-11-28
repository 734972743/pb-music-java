package com.pb.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pb.weixin.dao.IMessageDao;
import com.pb.weixin.service.IMessageService;
import com.pb.weixin.vo.Message;

@Service
public class MessageService implements IMessageService {
	
	@Autowired
	private IMessageDao messageDao;

	@Override
	public long getCountBySongId(Message message) {
		// TODO Auto-generated method stub
		Example example = Example.of(message);
		return messageDao.count(example);
	}

	@Override
	public Page<Message> getMessagesBySongId(Message message,Pageable pageable) {
		Example example = Example.of(message);
		return messageDao.findAll(example, pageable);
	}

	@Override
	public void replyMessage(Message message) {
		// TODO Auto-generated method stub
		messageDao.save(message);
	}
	
	

}
