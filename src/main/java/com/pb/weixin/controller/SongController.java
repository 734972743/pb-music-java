package com.pb.weixin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.pb.weixin.service.ISingerService;
import com.pb.weixin.service.ISongService;
import com.pb.weixin.utils.BaseResult;
import com.pb.weixin.utils.Page;
import com.pb.weixin.vo.Singer;
import com.pb.weixin.vo.Song;

@RestController
@RequestMapping("/song")

public class SongController {
	
	@Autowired
	private ISongService songService;
	
	@Autowired
	private ISingerService singerService;

	@RequestMapping(value="/getSongsAll", method=RequestMethod.GET)
	public BaseResult<List<Song>> getSongsAll(){
		BaseResult<List<Song>> result = new BaseResult<List<Song>>();
		try {
			List<Song> songs = songService.getSongsAll();
			result.setCode(200);
			result.setData(songs);
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
	public BaseResult<List<Song>> queryListPage(@PathVariable("curPage") int curPage, @PathVariable("pageSize")int pageSize, @RequestBody Song song){
		Page page = new Page(curPage,pageSize);
		
		BaseResult<List<Song>> result = new BaseResult<List<Song>>();
		
		result = (BaseResult<List<Song>>) songService.queryListPage(page,song);
		
		
		return result;
	}
	
	
	
	@RequestMapping(value="/getSongsBy", method=RequestMethod.POST)
	public BaseResult<List<Song>> getSongsBy(@RequestBody Song song){
		BaseResult<List<Song>> result = new BaseResult<List<Song>>();
		try {
			List<Song> songs = songService.getSongsBy(song);
			result.setCode(200);
			result.setData(songs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(500);
			result.setData(null);
		}
		return result;
	}
	
	
	//根据用户上传歌曲
	@RequestMapping(value="/addSong/{singerName}", method=RequestMethod.POST)
	public BaseResult<Integer> addSong(@PathVariable("singerName") String singerName, @RequestBody Song song){
		BaseResult<Integer> result = new BaseResult<Integer>();
		int singerId = 0 ;
		try {
			if(!singerName.equals("")) {
				//首先先存歌手信息
				Singer singer = new Singer();
				singer.setSingerName(singerName);
				List<Singer> singers = singerService.getSingersBy(singer);
				if(singers.size() == 0) {
					singerId = singerService.addSingerName(singer);
				}
			}
			
			//再保存歌曲信息
			if(singerId > 0) {
				song.setSingerId(singerId);
			}
			int data = songService.addSong(song);
			if(data>0) {
				result.setCode(200);
				result.setData(data);
			}else {
				result.setCode(500);
				result.setData(null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(500);
			result.setData(null);
		}
		return result;
	}
	
	
	
	//根据用户ID来查询自己收藏的歌曲信息
	@RequestMapping(value="/getCollectionSongByUserId/{userId}", method=RequestMethod.GET)
	public BaseResult<List<Song>> getCollectionSongByUserId(@PathVariable("userId") int userId){
		BaseResult<List<Song>> result = new BaseResult<List<Song>>();
		try {
			List<Song> songs = songService.getCollectionSongByUserId(userId);
			result.setCode(200);
			result.setData(songs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(500);
			result.setData(null);
		}
		return result;
	}
	
	//根据收藏夹ID来查询所有歌曲列表
	@RequestMapping(value="/getSongListBySongListId/{songListId}", method=RequestMethod.GET)
	public BaseResult<List<Song>> getSongListBySongListId(@PathVariable("songListId") int songListId){
		BaseResult<List<Song>> result = new BaseResult<List<Song>>();
		try {
			List<Song> songs = songService.getSongListBySongListId(songListId);
			result.setCode(200);
			result.setData(songs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(500);
			result.setData(null);
		}
		return result;
	}
}
