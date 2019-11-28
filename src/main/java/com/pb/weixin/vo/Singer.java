package com.pb.weixin.vo;

import java.util.Date;

public class Singer {

	private Integer singerId;   //歌手编号
	private String singerName;   //歌手名称
	private Integer accessCount;   //热度，即访问次数，默认0
	private Integer collectionCount;   //收藏次数，默认0
	private Integer areaId;    //地区编号，外键
	private String introduce;   //歌手简介（富文本）
	private Date birthday;    //歌手生日
	private Integer singerSex;   //歌手性别
	private String photoUrl;    //歌手图片链接地址
	private Date debutDate;    //歌手出道时间
	private Integer singerStateId;    //歌手状态信息编号（可拓展字段），默认为0，0表示正常状态，1表示阻塞状态
	
	
	
	public Integer getSingerId() {
		return singerId;
	}
	public void setSingerId(Integer singerId) {
		this.singerId = singerId;
	}
	public String getSingerName() {
		return singerName;
	}
	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}
	public Integer getAccessCount() {
		return accessCount;
	}
	public void setAccessCount(Integer accessCount) {
		this.accessCount = accessCount;
	}
	public Integer getCollectionCount() {
		return collectionCount;
	}
	public void setCollectionCount(Integer collectionCount) {
		this.collectionCount = collectionCount;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getSingerSex() {
		return singerSex;
	}
	public void setSingerSex(Integer singerSex) {
		this.singerSex = singerSex;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public Date getDebutDate() {
		return debutDate;
	}
	public void setDebutDate(Date debutDate) {
		this.debutDate = debutDate;
	}
	public Integer getSingerStateId() {
		return singerStateId;
	}
	public void setSingerStateId(Integer singerStateId) {
		this.singerStateId = singerStateId;
	}
	
	

	
	
}
