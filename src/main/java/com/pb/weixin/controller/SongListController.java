package com.pb.weixin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pb.weixin.service.ISongListService;
import com.pb.weixin.utils.BaseResult;
import com.pb.weixin.utils.Page;
import com.pb.weixin.vo.SongList;

@RestController
@RequestMapping(value="/songList")
public class SongListController {

	@Autowired
	private ISongListService iSongListService;
	
	//根据条件查询并带分页效果
		@RequestMapping(value="/getSongListsBy", method=RequestMethod.POST)
		public BaseResult<List<SongList>> getSongListsBy(@RequestBody SongList songList){
			Page page = new Page();
			BaseResult<List<SongList>> result = new BaseResult<List<SongList>>();
			List<SongList> data = new ArrayList<SongList>();
			try {
				data =  iSongListService.getSongListsBy(songList);
				result.setCode(200);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				result.setCode(500);
				e.printStackTrace();
			}
			result.setData(data);
			return result;
		}
		
		@RequestMapping(value="/addSongList", method=RequestMethod.POST)
		public BaseResult<Integer> addSongList(@RequestBody SongList songList){
			Page page = new Page();
			BaseResult<Integer> result = new BaseResult<Integer>();
			
			int songListId = 0;
			try {
				 iSongListService.addSongList(songList);
				songListId = songList.getSongListId();
				if(songListId>0) {
					result.setCode(200);	
				}else {
					result.setCode(500);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.setCode(500);
			}
			result.setData(songListId);
			return result;
		}
		
		@RequestMapping(value="/updateSongList", method=RequestMethod.PUT)
		public BaseResult<Integer> updateSongList(@RequestBody SongList songList){
			Page page = new Page();
			BaseResult<Integer> result = new BaseResult<Integer>();
			
			int data  = 0;
			try {
				iSongListService.updateSongList(songList);
				data = songList.getSongListId();   //取返回的ID
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
		
		
		//getgetSongListsByUserId  根据用户ID来获取所有的收藏夹信息
		@RequestMapping(value="/getgetSongListsByUserId/{userId}", method=RequestMethod.GET)
		public BaseResult<List<SongList>> getgetSongListsByUserId(@PathVariable int userId){
			Page page = new Page();
			BaseResult<List<SongList>> result = new BaseResult<List<SongList>>();
			List<SongList> data = new ArrayList<SongList>();
			try {
				data =  iSongListService.getgetSongListsByUserId(userId);
				result.setCode(200);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				result.setCode(500);
				e.printStackTrace();
			}
			result.setData(data);
			return result;
		}
}
