package com.pb.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pb.weixin.dao.IUserDao;
import com.pb.weixin.service.IUserService;
import com.pb.weixin.vo.User;

@Service
public class UserService implements IUserService {
	
	@Autowired
	public IUserDao userDao;
	
	public List<User> getUsers(User user) {
		// TODO Auto-generated method stub
		return userDao.getUsers(user);
	}

	@Override
	public User getUserByLoginIdAndPassword(String loginId, String password) {
		// TODO Auto-generated method stub
		return userDao.getUserByLoginIdAndPassword(loginId, password);
	}

	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
		int i = userDao.saveUser(user);
		return i;
	}

	@Override
	public List<User> getUserBySearch(User user) {
		// TODO Auto-generated method stub
	   return userDao.getUserBySearch(user);
	}

	@Override
	public int updateUserByCondition(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUserByCondition(user);
	}

	@Override
	public int deleteUserById(User user) {
		// TODO Auto-generated method stub
		return userDao.deleteUserById(user);
	}

	@Override
	public int getByLoginId(String loginId) {
		// TODO Auto-generated method stub
		return userDao.getByLoginId(loginId);
	}

	
}
