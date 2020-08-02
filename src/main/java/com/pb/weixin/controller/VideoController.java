package com.pb.weixin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pb.weixin.service.IVideoService;
import com.pb.weixin.utils.BaseResult;
import com.pb.weixin.utils.Page;
import com.pb.weixin.vo.Song;
import com.pb.weixin.vo.Video;

@RestController
@RequestMapping(value="/video")
public class VideoController {
	
	@Autowired
	private IVideoService videoService;

	@RequestMapping(value="/getVideosBy", method=RequestMethod.POST)
	public BaseResult<List<Video>> getVideosBy(@RequestBody Video video){
		BaseResult<List<Video>> result = new BaseResult<List<Video>>();
		List<Video> data = new ArrayList<Video>();
		try {
			data = videoService.getVideosBy(video);	
			result.setCode(200);
			result.setData(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(500);
			result.setData(null);
		}
		return result;
	}
	
	//根据条件查询并带分页效果
	@RequestMapping(value="/queryListPage/{curPage}/{pageSize}", method=RequestMethod.POST)
	public BaseResult<List<Video>> queryListPage(@PathVariable("curPage") int curPage, @PathVariable("pageSize")int pageSize, @RequestBody Video video){
		Page page = new Page(curPage,pageSize);
		
		BaseResult<List<Video>> result = new BaseResult<List<Video>>();
		
		result = (BaseResult<List<Video>>) videoService.queryListPage(page,video);
		
		
		return result;
	}
}
