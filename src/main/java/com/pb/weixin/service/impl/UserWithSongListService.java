package com.pb.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pb.weixin.dao.IUserWithSongListDao;
import com.pb.weixin.service.IUserWithSongListService;
import com.pb.weixin.vo.UserWithSongList;

@Service
public class UserWithSongListService implements IUserWithSongListService {
	
	@Autowired
	private IUserWithSongListDao iUserWithSongListDao;

	@Override
	public List<UserWithSongList> getUserWithSongListsBy(UserWithSongList userWithSongList) {
		// TODO Auto-generated method stub
		return iUserWithSongListDao.getUserWithSongListsBy(userWithSongList);
	}

	@Override
	public int addUserWithSongList(UserWithSongList userWithSongList) {
		// TODO Auto-generated method stub
		return iUserWithSongListDao.addUserWithSongList(userWithSongList);
	}

	@Override
	public int updateUserWithSongList(UserWithSongList userWithSongList) {
		// TODO Auto-generated method stub
		return iUserWithSongListDao.updateUserWithSongList(userWithSongList);
	}

	@Override
	public int deleteUserWithSongList(UserWithSongList userWithSongList) {
		// TODO Auto-generated method stub
		return iUserWithSongListDao.deleteUserWithSongList(userWithSongList);
	}

}
