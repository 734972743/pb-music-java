package com.pb.weixin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pb.weixin.vo.SongListWithSong;

public interface ISongListWithSongService {

	//根据条件来查询歌曲收藏列表
    public List<SongListWithSong> getSongListWithSongBy(SongListWithSong songListWithSong);
    
    public int addSongListWithSong(SongListWithSong songListWithSong);
    
    public int deleteSongListWithSong(SongListWithSong songListWithSong);
    
    public int updateSongListWithSong(SongListWithSong songListWithSong);
	
}
