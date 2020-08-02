package com.pb.weixin.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name="t_video")
public class Video implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	private String videoName;
	private String videoUrl;
	private String videoImageUrl;
	private String videoSource;
	private String videoIntroduce;
	private Integer videoType;
	private Date createDate;
	

	@Transient
	private int isSort;  // 0 按时间升序 ， 1按时间降序
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getVideoImageUrl() {
		return videoImageUrl;
	}
	public void setVideoImageUrl(String videoImageUrl) {
		this.videoImageUrl = videoImageUrl;
	}
	public String getVideoSource() {
		return videoSource;
	}
	public void setVideoSource(String videoSource) {
		this.videoSource = videoSource;
	}
	public String getVideoIntroduce() {
		return videoIntroduce;
	}
	public void setVideoIntroduce(String videoIntroduce) {
		this.videoIntroduce = videoIntroduce;
	}
	public Integer getVideoType() {
		return videoType;
	}
	public void setVideoType(Integer videoType) {
		this.videoType = videoType;
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
