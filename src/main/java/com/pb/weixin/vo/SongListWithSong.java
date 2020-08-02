package com.pb.weixin.vo;

import java.io.Serializable;
import java.util.Date;


//歌单收藏歌曲vo
public class SongListWithSong implements Serializable{

	public Integer slSongId;  //歌曲歌单编号
	public Integer songListId;  //歌单编号，外键
	public Integer songId;  //歌曲编号，外键
	public Date collectionDate;  //收藏日期
	
	
	
	public Integer getSlSongId() {
		return slSongId;
	}
	public void setSlSongId(Integer slSongId) {
		this.slSongId = slSongId;
	}
	
	public Integer getSongListId() {
		return songListId;
	}
	public void setSongListId(Integer songListId) {
		this.songListId = songListId;
	}
	public Integer getSongId() {
		return songId;
	}
	public void setSongId(Integer songId) {
		this.songId = songId;
	}
	public Date getCollectionDate() {
		return collectionDate;
	}
	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}
	
	
	
	
}
