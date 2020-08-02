package com.pb.weixin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pb.weixin.vo.Song;

public interface ISongDao extends BaseDao<Song> {

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
	public int addSong(@Param("t") Song song);
	
	/**
	 * 修改一首音乐
	 * @param song
	 * @return
	 */
	public int updateSong(@Param("t") Song song);
	
	
	/**
	 * 删除一首音乐
	 * @param song
	 * @return
	 */
	public int deleteSong(@Param("t") Song song);
	
	
	//根据用户ID来查询自己收藏的歌曲信息
	public List<Song> getCollectionSongByUserId(@Param("userId") int userId);
	
	//根据收藏夹ID来查询所有歌曲列表
	public List<Song> getSongListBySongListId(@Param("songListId") int songListId);
	
	
	
}
