package com.pb.weixin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pb.weixin.vo.UserWithSongList;

public interface IUserWithSongListDao extends BaseDao<UserWithSongList> {

	//根据条件来查询歌曲收藏列表
	public List<UserWithSongList> getUserWithSongListsBy(@Param("t") UserWithSongList t);
	
	public int addUserWithSongList(@Param("t") UserWithSongList t);
	
	public int updateUserWithSongList(@Param("t") UserWithSongList t);
	
	public int deleteUserWithSongList(@Param("t") UserWithSongList t);
	
	
}
