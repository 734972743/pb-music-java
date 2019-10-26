package com.pb.weixin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pb.weixin.vo.User;

public interface IUserDao {

	public List<User> getUsers(@Param("t") User t);
	
	public User getUserByLoginIdAndPassword(@Param("loginId")String loginId,@Param("password")String password);
	
	public int getByLoginId(@Param("loginId")String loginId);
	
	public int  saveUser(User user);
	
	
	//根据条件动态的查询users
	public List<User> getUserBySearch(User user);

	//根据条件动态修改user
	public int updateUserByCondition(User user);
	
	//删除User
	public int deleteUserById(User user);
	
}
