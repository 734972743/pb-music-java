package com.pb.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pb.weixin.dao.ISongDao;
import com.pb.weixin.dao.IUserHistorySongDao;
import com.pb.weixin.service.ISongService;
import com.pb.weixin.service.IUserHistorySongService;
import com.pb.weixin.utils.Page;
import com.pb.weixin.vo.Song;
import com.pb.weixin.vo.UserHistorySong;

@Service
public class UserHistorySongService extends BaseService<UserHistorySong> implements IUserHistorySongService {

	@Autowired
	private IUserHistorySongDao userHistorySongDao;

	@Override
	public List<UserHistorySong> getUserHistorySongsByUserId(int userId) {
		// TODO Auto-generated method stub
		return userHistorySongDao.getUserHistorySongsByUserId(userId);
	}

	@Override
	public int addUserHistorySong(UserHistorySong t) {
		// TODO Auto-generated method stub
		return userHistorySongDao.addUserHistorySong(t);
	}

	@Override
	public int deleteAllHistorySongByUserId(int userId) {
		// TODO Auto-generated method stub
		return userHistorySongDao.deleteAllHistorySongByUserId(userId);
	}
	


	

	

}
