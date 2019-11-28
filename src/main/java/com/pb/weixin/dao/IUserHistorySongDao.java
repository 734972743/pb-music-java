package com.pb.weixin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pb.weixin.vo.Song;
import com.pb.weixin.vo.UserHistorySong;

public interface IUserHistorySongDao extends BaseDao<UserHistorySong> {

	
	
	//根据用户ID来查询自己收听过的歌曲历史信息 
	public List<UserHistorySong> getUserHistorySongsByUserId(@Param("userId") int userId);
	
	public int addUserHistorySong(@Param("t") UserHistorySong t);
	
	//根据用户ID来清空他所有的历史记录
	public int deleteAllHistorySongByUserId(@Param("userId") int userId);	
}
