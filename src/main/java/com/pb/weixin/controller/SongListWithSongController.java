package com.pb.weixin.controller;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pb.weixin.service.ISongListService;
import com.pb.weixin.service.ISongListWithSongService;
import com.pb.weixin.utils.BaseResult;
import com.pb.weixin.utils.DateFormatUtil;
import com.pb.weixin.utils.Page;
import com.pb.weixin.vo.SongList;
import com.pb.weixin.vo.SongListWithSong;

@RestController
@RequestMapping(value="/songListWithSong")
public class SongListWithSongController {

	@Autowired
	private ISongListWithSongService iSongListWithSongService;
	
	@Autowired
	private ISongListService iSongListService; 
	
	
	//根据条件查询并带分页效果
	@RequestMapping(value="/getSongListWithSongBy", method=RequestMethod.POST)
	public BaseResult<List<SongListWithSong>> getSongListWithSongBy(@RequestBody SongListWithSong songListWithSong){
		Page page = new Page();
		BaseResult<List<SongListWithSong>> result = new BaseResult<List<SongListWithSong>>();
		List<SongListWithSong> data = new ArrayList<SongListWithSong>();
		try {
			data =  iSongListWithSongService.getSongListWithSongBy(songListWithSong);
			result.setCode(200);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setCode(500);
			e.printStackTrace();
		}
		result.setData(data);
		return result;
	}
	
	
	
	//收藏歌曲
	@RequestMapping(value = "/addSongListWithSong", method = RequestMethod.POST)
	public BaseResult<Integer> addSongListWithSong(@RequestBody SongListWithSong songListWithSong) {
		BaseResult<Integer> result  = new BaseResult<Integer>();
		int data= 0;
		try {
			songListWithSong.setCollectionDate(DateFormatUtil.getNowDateShort());
			
			data = iSongListWithSongService.addSongListWithSong(songListWithSong);
			
			
			if(data>0) {
				//每次收藏歌曲的时候，收藏夹里面的 收藏的歌曲的数量 应加 1
				int songListId = songListWithSong.getSongListId();
				SongList songList = new SongList();
				songList.setSongListId(songListId);
				SongList songListData =  iSongListService.getSongListsBy(songList).get(0);
				int collectionCount = songListData.getCollectionCount() +1 ;  //收藏夹里面的 收藏的歌曲的数量 应加 1
				songListData.setCollectionCount(collectionCount);
				iSongListService.updateSongList(songListData);
				result.setCode(200);
			}else {
				result.setCode(500);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(500);
		}
		result.setData(data);
		return result;
	}
	
	//取消收藏
	@RequestMapping(value = "/deleteSongListWithSong", method = RequestMethod.DELETE)
	public BaseResult<Integer> deleteSongListWithSong(@RequestBody SongListWithSong songListWithSong) {
		BaseResult<Integer> result  = new BaseResult<Integer>();
		int data= 0;
		try {
			
			data = iSongListWithSongService.deleteSongListWithSong(songListWithSong);
			if(data>0) {
				result.setCode(200);
			}else {
				result.setCode(500);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(500);
		}
		result.setData(data);
		return result;
	} 
}
