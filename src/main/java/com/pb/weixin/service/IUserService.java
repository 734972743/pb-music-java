package com.pb.weixin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pb.weixin.vo.User;

public interface IUserService {

	
	public List<User> getUsers(User user);
	
	public User getUserByLoginIdAndPassword(String loginId,String password);
	
	public int getByLoginId(@Param("loginId")String loginId);
	
	public int  saveUser(User user);
	
	public List<User> getUserBySearch(User user);
	
	//根据条件动态修改user
	public int updateUserByCondition(User user);
	
	//删除User
	public int deleteUserById(User user);
}
