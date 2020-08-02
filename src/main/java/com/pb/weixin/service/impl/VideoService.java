package com.pb.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pb.weixin.dao.IVideoDao;
import com.pb.weixin.service.IVideoService;
import com.pb.weixin.vo.Video;

@Service
public class VideoService extends BaseService<Video> implements IVideoService {

	@Autowired
	private IVideoDao videoDao;
	
	@Override
	public List<Video> getVideosBy(Video video) {
		// TODO Auto-generated method stub
		return videoDao.getVideosBy(video);
	}

	

}
