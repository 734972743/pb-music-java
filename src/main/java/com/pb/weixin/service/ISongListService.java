package com.pb.weixin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pb.weixin.vo.SongList;

public interface ISongListService {

	
	//根据条件来查询歌曲收藏列表
    public List<SongList> getSongListsBy(SongList songList);
    
    public int addSongList(SongList songList);
    
    public int deleteSongList(SongList songList);
    
    public int updateSongList(SongList songList);
    
    public List<SongList> getgetSongListsByUserId(@Param("userId") int userId);
}
