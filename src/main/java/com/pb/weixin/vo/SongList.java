package com.pb.weixin.vo;


//这个是歌单收藏vo
public class SongList {
	
	private Integer songListId;   //歌单编号
	private String songListName;   //歌单名称
	private Integer typeId;      //类型编号，外键
	private Integer accessAount;   //热度，访问次数，默认0
	private String introduce;      //歌单简介
	private Integer collectionCount;   //收藏次数(收藏的歌曲的数量)，默认0
	private Integer tags;      //歌单标签
	private Integer songListStateId;   //歌单状态信息编号（可拓展字段）默认0，0:公开发布状态，1:私有状态（vip用户）
	
	
	public Integer getSongListId() {
		return songListId;
	}
	public void setSongListId(Integer songListId) {
		this.songListId = songListId;
	}
	public String getSongListName() {
		return songListName;
	}
	public void setSongListName(String songListName) {
		this.songListName = songListName;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getAccessAount() {
		return accessAount;
	}
	public void setAccessAount(Integer accessAount) {
		this.accessAount = accessAount;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Integer getCollectionCount() {
		return collectionCount;
	}
	public void setCollectionCount(Integer collectionCount) {
		this.collectionCount = collectionCount;
	}
	public Integer getTags() {
		return tags;
	}
	public void setTags(Integer tags) {
		this.tags = tags;
	}
	public Integer getSongListStateId() {
		return songListStateId;
	}
	public void setSongListStateId(Integer songListStateId) {
		this.songListStateId = songListStateId;
	}

	
	

}
