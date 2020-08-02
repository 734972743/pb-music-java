package com.pb.weixin.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;


public class Song implements Serializable{

	
	private Integer songId; // 歌曲编号

	private String songName; // 歌曲录入时的名称
	private Integer singerId; // 歌手编号
	private Integer cdId; // CD专辑编号
	private String language; // 歌曲的语种
	private Integer playCount; // 歌曲播放次数
	private Integer downloadCount; // 歌曲下载次数
	private Integer collectionCount; // 歌曲收藏次数
	private Date publishDate; // 歌曲发行时间（年月日）
	private String songUrl; // 歌曲的链接地址
	private String cyricUrl; // 歌词的链接地址
	private String imgUrl; // 歌词的链接地址
	private Integer songTime; // 歌曲的时间长度(单位:秒)
	private Integer typeId; // 歌曲的类型编号
	private List<Integer> searchTypeIds; // 这是用来查询多个类型编号的歌曲的查询条件
	private Date createDate;    //创建时间
	
	
	private Integer songStateId; // 歌曲状态信息编号（可拓展字段）默认0;0正常发布状态1阻塞状态（不显示）2禁止下载 3条件下载（付费，待定）
	
	@Transient
	private int isSort;  // 0 按时间升序 ， 1按时间降序

	public Integer getSongId() {
		return songId;
	}

	public void setSongId(Integer songId) {
		this.songId = songId;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public Integer getSingerId() {
		return singerId;
	}

	public void setSingerId(Integer singerId) {
		this.singerId = singerId;
	}

	public Integer getCdId() {
		return cdId;
	}

	public void setCdId(Integer cdId) {
		this.cdId = cdId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getPlayCount() {
		return playCount;
	}

	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}

	public Integer getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

	public Integer getCollectionCount() {
		return collectionCount;
	}

	public void setCollectionCount(Integer collectionCount) {
		this.collectionCount = collectionCount;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getSongUrl() {
		return songUrl;
	}

	public void setSongUrl(String songUrl) {
		this.songUrl = songUrl;
	}

	public String getCyricUrl() {
		return cyricUrl;
	}

	public void setCyricUrl(String cyricUrl) {
		this.cyricUrl = cyricUrl;
	}

	public Integer getSongTime() {
		return songTime;
	}

	public void setSongTime(Integer songTime) {
		this.songTime = songTime;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public List<Integer> getSearchTypeIds() {
		return searchTypeIds;
	}

	public void setSearchTypeIds(List<Integer> searchTypeIds) {
		this.searchTypeIds = searchTypeIds;
	}

	public Integer getSongStateId() {
		return songStateId;
	}

	public void setSongStateId(Integer songStateId) {
		this.songStateId = songStateId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getIsSort() {
		return isSort;
	}

	@Transient
	public void setIsSort(int isSort) {
		this.isSort = isSort;
	}

	
}
