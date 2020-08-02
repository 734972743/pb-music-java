package com.pb.weixin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pb.weixin.service.ISongService;
import com.pb.weixin.service.IUserHistorySongService;
import com.pb.weixin.utils.BaseResult;
import com.pb.weixin.utils.Page;
import com.pb.weixin.vo.Song;
import com.pb.weixin.vo.UserHistorySong;

@RestController
@RequestMapping("/userHistorySong")

public class UserHistorySongController {
	
	@Autowired
	private IUserHistorySongService userHistorySongService;

	
	
	//根据用户ID来查询自己收听过的歌曲历史信息
	@RequestMapping(value="/getUserHistorySongsByUserId/{userId}", method=RequestMethod.GET)
	public BaseResult<List<UserHistorySong>> getUserHistorySongsByUserId(@PathVariable("userId") int userId){
		BaseResult<List<UserHistorySong>> result = new BaseResult<List<UserHistorySong>>();
		try {
			List<UserHistorySong> userHistorySongs = userHistorySongService.getUserHistorySongsByUserId(userId);
			
			result.setCode(200);
			result.setData(userHistorySongs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(500);
			result.setData(null);
		}
		return result;
	}
	
	//添加自己收听的歌曲
		@RequestMapping(value="/addUserHistorySong", method=RequestMethod.POST)
		public BaseResult<Integer> addUserHistorySong(@RequestBody UserHistorySong userHistorySong){
			BaseResult<Integer> result = new BaseResult<Integer>();
			try {
				
				//先判断是否最近一次已经收听了改歌曲
				UserHistorySong uhs = userHistorySongService.getUserHistorySongByUserIdAndSongId(userHistorySong);
				if(uhs == null) {
					userHistorySong.setHistoryDate(new Date());
					int data = userHistorySongService.addUserHistorySong(userHistorySong);
					
					if(data > 0) {
						result.setCode(200);
						result.setData(data);
					}else {
						result.setCode(500);
						result.setData(null);
					}
				}else {
					//更新时间
					uhs.setHistoryDate(new Date());
					userHistorySongService.updateHistorySongByUhsId(uhs);
					result.setCode(200);
					result.setData(2);  //随便写个值
				}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.setCode(500);
				result.setData(null);
			}
			return result;
		}
		
		//根据用户ID来清空他所有的历史记录
		@RequestMapping(value="/deleteAllHistorySongByUserId/{userId}", method=RequestMethod.DELETE)
		public BaseResult<Integer> deleteAllHistorySongByUserId(@PathVariable("userId") int userId){
			BaseResult<Integer> result = new BaseResult<Integer>();
			try {
				int data = userHistorySongService.deleteAllHistorySongByUserId(userId);
				if(data > 0) {
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
}
