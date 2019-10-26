package com.pb.weixin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pb.weixin.vo.SongList;

public interface ISongListDao {

	
	//根据条件来查询歌曲收藏列表
    public List<SongList> getSongListsBy(@Param("t") SongList t);
    
    public int addSongList(@Param("t") SongList t);
    
    public int deleteSongList(@Param("t") SongList t);
    
    public int updateSongList(@Param("t") SongList t);
    
    public List<SongList> getgetSongListsByUserId(@Param("userId") int userId);
}
