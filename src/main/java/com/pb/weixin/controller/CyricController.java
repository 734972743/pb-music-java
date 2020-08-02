package com.pb.weixin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pb.weixin.service.CyricService;
import com.pb.weixin.utils.BaseResult;
import com.pb.weixin.utils.Page;
import com.pb.weixin.vo.Cyric;
import com.pb.weixin.vo.SongList;

@RestController
@RequestMapping(value="/cyric")
public class CyricController {

	@Autowired
	private CyricService cyricService;
	
	//根据条件查询并带分页效果
	@RequestMapping(value="/getAllCyricsByCyric", method=RequestMethod.POST)
	public BaseResult<List<Cyric>> getAllCyricsBy(@RequestBody Cyric cyric){
		Page page = new Page();
		BaseResult<List<Cyric>> result = new BaseResult<List<Cyric>>();
		List<Cyric> data = new ArrayList<Cyric>();
		try {
			data =  cyricService.getAllCyricsByCyric(cyric);
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
