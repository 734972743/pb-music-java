package com.pb.weixin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pb.weixin.vo.Video;

public interface IVideoDao extends BaseDao<Video>{
	
	public List<Video> getVideosBy(@Param("t") Video t);
	
	
	
}