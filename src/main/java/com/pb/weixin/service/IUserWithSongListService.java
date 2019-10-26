package com.pb.weixin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pb.weixin.vo.UserWithSongList;

public interface IUserWithSongListService {

	
	public List<UserWithSongList> getUserWithSongListsBy(UserWithSongList userWithSongList);
	
	public int addUserWithSongList(UserWithSongList userWithSongList);
	
	public int updateUserWithSongList(UserWithSongList userWithSongList);
	
	public int deleteUserWithSongList(UserWithSongList userWithSongList);
	
}
