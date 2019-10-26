package com.pb.weixin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pb.weixin.vo.SongListWithSong;

public interface ISongListWithSongDao {

	
	//根据条件来查询歌曲收藏列表
    public List<SongListWithSong> getSongListWithSongBy(@Param("t") SongListWithSong t);
    
    public int addSongListWithSong(@Param("t") SongListWithSong t);
    
    public int deleteSongListWithSong(@Param("t") SongListWithSong t);
    
    public int updateSongListWithSong(@Param("t") SongListWithSong t);
    
    
    
    
}
