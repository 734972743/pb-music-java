package com.pb.weixin.controller;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.fabric.xmlrpc.base.Data;
import com.pb.weixin.service.ISongListService;
import com.pb.weixin.service.IUserWithSongListService;
import com.pb.weixin.utils.BaseResult;
import com.pb.weixin.utils.DateFormatUtil;
import com.pb.weixin.utils.Page;
import com.pb.weixin.vo.SongList;
import com.pb.weixin.vo.UserWithSongList;

@RestController
@RequestMapping("/userWithSongList")
public class UserWithSongListController {

	@Autowired
	private IUserWithSongListService iUserWithSongListService;

	@Autowired
	private ISongListService iSongListService;

	// 根据条件查询并带分页效果
	@RequestMapping(value = "/getUserWithSongListsBy", method = RequestMethod.POST)
	public BaseResult<List<UserWithSongList>> getUserWithSongListsBy(@RequestBody UserWithSongList userWithSongList) {
		Page page = new Page();
		BaseResult<List<UserWithSongList>> result = new BaseResult<List<UserWithSongList>>();
		List<UserWithSongList> data = new ArrayList<UserWithSongList>();

		try {
			data = iUserWithSongListService.getUserWithSongListsBy(userWithSongList);
			result.setCode(200);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setCode(500);
			e.printStackTrace();
		}
		result.setData(data);
		return result;
	}

	// addUserWithSongList
	// 新建一张收藏夹
	@RequestMapping(value = "/addUserWithSongList", method = RequestMethod.POST)
	public BaseResult<Integer> addUserWithSongList(@RequestBody HashMap<String, Object> map) {
		BaseResult<Integer> result = new BaseResult<Integer>();

		// 1.先在收藏夹表中添加一条收藏夹信息 t_song_list ,并返回这条记录的ID
		// 2. 根据第一步的ID，和用户ID,向 t_user_with_song_list 表添加一条记录，
		// 再把添加记录的结果返回给前台

		SongList songList = new SongList();
		songList.setSongListName((String) map.get("collectionName"));
		songList.setIntroduce((String) map.get("introduce"));
		songList.setSongListStateId(Integer.parseInt((String) map.get("songListStateId")));

		// 返回结果的ID
		int data = 0;
		try {
			iSongListService.addSongList(songList);
			int songListId = songList.getSongListId(); // 取主键ID

			if (songListId > 0) {

				UserWithSongList userWithSongList = new UserWithSongList();
				userWithSongList.setUserId((Integer) map.get("userId"));
				userWithSongList.setSongListId(songListId);

//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//				String dateStr = sdf.format(new Date());
//				Date date = new Date(dateStr);
				// user.setRegistationDate(getNowDateShort());
				userWithSongList.setCollectionDate(DateFormatUtil.getNowDateShort());

				iUserWithSongListService.addUserWithSongList(userWithSongList);
				data = userWithSongList.getSongListId();
				if (data > 0) {
					result.setCode(200);
				} else {
					result.setCode(500);
				}
			} else {
				result.setCode(500);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// result.setCode(500);
			e.printStackTrace();
			result.setCode(500);
		}

		result.setData(data);
		return result;
	}

	// 根据收藏夹ID来删除收藏夹 deleteUserWithSongList
	@RequestMapping(value = "/deleteUserWithSongList/{songListId}/{userId}", method = RequestMethod.DELETE)
	public BaseResult<Integer> deleteUserWithSongList(@PathVariable("songListId") int songListId, @PathVariable("userId") int userId) {
		BaseResult<Integer> result = new BaseResult<Integer>();
		SongList songList = new SongList();
		songList.setSongListId(songListId);
		
		
		UserWithSongList userWithSongList = new UserWithSongList();
		userWithSongList.setUserId(userId);
		userWithSongList.setSongListId(songListId);
		// 返回结果的ID
		int data1 = 0;
		int data2 = 0;
		try {

				//先删除字表
			data1 = iSongListService.deleteSongList(songList);
			
			data2 = iUserWithSongListService.deleteUserWithSongList(userWithSongList);

			if (data1 > 0 && data2 > 0) {
				result.setCode(200);
				result.setMessage("删除成功");
			} else {
				result.setCode(500);
				result.setMessage("删除失败");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// result.setCode(500);
			e.printStackTrace();
			result.setCode(500);
			result.setMessage("删除失败");
		}

		result.setData(data2);
		return result;
	}

}
