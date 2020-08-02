package com.pb.weixin.service;

import java.util.List;

import com.pb.weixin.utils.BaseResult;
import com.pb.weixin.utils.Page;
import com.pb.weixin.vo.Song;
import com.pb.weixin.vo.Video;

public interface IVideoService extends IBaseService<Video> {
	
	//根据条件来过去所有的视频数据
	public List<Video> getVideosBy(Video video);
	
	 
}
