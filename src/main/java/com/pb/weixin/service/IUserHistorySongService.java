package com.pb.weixin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pb.weixin.vo.UserHistorySong;


public interface IUserHistorySongService extends IBaseService<UserHistorySong> {

	
	
	//根据用户ID来查询自己收听过的歌曲历史信息 
	public List<UserHistorySong> getUserHistorySongsByUserId(int userId);
	
	//根据用户id和歌曲id来查找歌曲
	public UserHistorySong getUserHistorySongByUserIdAndSongId(UserHistorySong t);
	
	public int addUserHistorySong(UserHistorySong t);
	
	//根据用户ID来清空他所有的历史记录
	public int deleteAllHistorySongByUserId(int userId);
	
	//修改
	public int updateHistorySongByUhsId(UserHistorySong t);
}
