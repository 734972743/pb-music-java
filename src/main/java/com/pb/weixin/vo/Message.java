package com.pb.weixin.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name="t_message")
public class Message  implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)   //表示是ID自增
	private Integer  id;
	

	
	
	private Integer  songId;
	private String  content;
	private Integer  likes;
	private Integer  userId;
	private Integer  toUserId;
	private Date  createTime;
	
	//private String userName;
	
	
//	private String userName;
//	private String toUserName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getSongId() {
		return songId;
	}
	public void setSongId(Integer songId) {
		this.songId = songId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getToUserId() {
		return toUserId;
	}
	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
//	public String getUserName() {
//		return userName;
//	}
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//	public String getToUserName() {
//		return toUserName;
//	}
//	public void setToUserName(String toUserName) {
//		this.toUserName = toUserName;
//	}
	
	
	

	
}
