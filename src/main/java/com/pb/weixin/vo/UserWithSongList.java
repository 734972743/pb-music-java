package com.pb.weixin.vo;

import java.util.Date;


//用户收藏歌单vo
public class UserWithSongList {

	public Integer uSongListId;  //用户歌单编号
	public Integer userId;  //用户编号，外键
	public Integer songListId;  //歌单编号，外键
	public Date collectionDate;  //收藏日期
	
	
	public Integer getuSongListId() {
		return uSongListId;
	}
	public void setuSongListId(Integer uSongListId) {
		this.uSongListId = uSongListId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getSongListId() {
		return songListId;
	}
	public void setSongListId(Integer songListId) {
		this.songListId = songListId;
	}
	public Date getCollectionDate() {
		return collectionDate;
	}
	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}
	
	
	
	
	
}
