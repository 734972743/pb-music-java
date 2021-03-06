package com.pb.weixin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pb.weixin.vo.Song;

public interface ISongService extends IBaseService<Song> {

	/**
	 * 查询所有的音乐
	 * @return
	 */
	public List<Song> getSongsAll();
	
	/**
	 * 根据条件来查询音乐
	 * @return
	 */
	public List<Song> getSongsBy(Song song);
	
	/**
	 * 增加一首音乐
	 * @param song
	 * @return
	 */
	public int addSong(Song song);
	
	/**
	 * 修改一首音乐
	 * @param song
	 * @return
	 */
	public int updateSong(Song song);
	
	
	/**
	 * 删除一首音乐
	 * @param song
	 * @return
	 */
	public int deleteSong(Song song);
	
	//根据用户ID来查询自己收藏的歌曲信息
	public List<Song> getCollectionSongByUserId(int userId);
}
