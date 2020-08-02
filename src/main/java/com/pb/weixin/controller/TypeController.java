package com.pb.weixin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pb.weixin.service.ITypeService;
import com.pb.weixin.utils.BaseResult;
import com.pb.weixin.utils.Page;
import com.pb.weixin.vo.Type;

@RestController
@RequestMapping("/type")
public class TypeController {

	@Autowired
	private ITypeService typeService;
	
	@RequestMapping(value="/getTypesAll", method=RequestMethod.GET)
	public BaseResult<List<Type>> getTypesAll() {
		BaseResult<List<Type>> result = new BaseResult<List<Type>>();
		try {
			List<Type> types = typeService.getTypesAll();
			result.setCode(200);
			result.setData(types);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(500);
			result.setData(null);
		}
		return result;
	}
	
	@RequestMapping(value="/getTypesBy", method=RequestMethod.POST)
	public BaseResult<List<Type>> getTypesBy(@RequestBody Type param) {
		BaseResult<List<Type>> result = new BaseResult<List<Type>>();
		try {
			List<Type> types = typeService.getTypesBy(param);
			result.setCode(200);
			result.setData(types);
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
		public BaseResult<List<Type>> queryListPage(@PathVariable("curPage") int curPage, @PathVariable("pageSize")int pageSize, @RequestBody Type type){
			Page page = new Page(curPage,pageSize);
			BaseResult<List<Type>> result = new BaseResult<List<Type>>();
			
			result = (BaseResult<List<Type>>) typeService.queryListPage(page,type);
			return result;
		}
}
