package com.pb.weixin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pb.weixin.service.IUserService;
import com.pb.weixin.utils.BaseResult;
import com.pb.weixin.vo.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userSerivce;
	
	@RequestMapping(value="/getUsers",method=RequestMethod.POST)
	public BaseResult<List<User>> getUses(@RequestBody User user) {
		BaseResult<List<User>> result  = new BaseResult<List<User>>();
		List<User> usersData = new ArrayList<User>();
		
		try {
			usersData = userSerivce.getUsers(user);
			result.setData(usersData);
			result.setFlag(true);
			result.setCode(200);
			result.setMessage("查询用户成功");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setData(usersData);
			result.setFlag(false);
			result.setCode(500);
			result.setMessage("查询用户失败");
		}
		return result;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public BaseResult<User> login(@RequestBody User userParam){
		BaseResult<User> result  = new BaseResult<User>();
		
		User user = userSerivce.getUserByLoginIdAndPassword(userParam.getLoginId(), userParam.getPassword());
		result.setData(user);
		
		if(user != null) {
			result.setFlag(true);
			result.setMessage("登录成功");
		}else {
			result.setFlag(false);
			result.setMessage("用户名或密码失败");
		}
		return result;
	}
	
	//验证账户是否存在
	@RequestMapping(value="/valiAccount/{loginId}",method=RequestMethod.GET)
	public BaseResult<Integer> valiAccount(@PathVariable("loginId")String loginId){
		BaseResult<Integer> result = new BaseResult<Integer>();
		int count = 0;
		try {
			count = userSerivce.getByLoginId(loginId);
			if(count<=0) {
				result.setCode(200);
			}else {
				result.setCode(500);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(500);
		}
		result.setData(count);
		return result;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public BaseResult<User> register(@RequestBody User user){
		BaseResult<User> result  = new BaseResult<User>();
		
		//"yyyy-MM-dd HH:mm:ss"
		user.setRegistationDate(new Date());
		
		int i = 0;
		 try {
			i = userSerivce.saveUser(user);
			if(i>0) {
				result.setCode(200);
				result.setMessage("注册成功");
				//再根据注册的账号和密码查找该用户信息
				User data = userSerivce.getUserByLoginIdAndPassword(user.getLoginId(), user.getPassword());
				result.setData(data);
			}else {
				result.setCode(500);
				result.setMessage("注册失败");
				result.setData(null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(500);
			result.setMessage("注册失败");
			result.setData(null);
		}
		return result;
	}
	
	
	//修改用户信息
	@RequestMapping(value="/updateUserInfo", method=RequestMethod.PUT)
	public BaseResult<User> updateUserInfo(@RequestBody User user){
		BaseResult<User> result  = new BaseResult<User>();
		int i = 0;
		 User usersData = null;
		 try {
			i = userSerivce.updateUserByCondition(user);
			if(i>0) {
				//修改成功，则返回修改成功的数据
				usersData = userSerivce.getUsers(user).get(0);
				
				result.setFlag(true);
				result.setMessage("修改成功");
				//再根据注册的账号和密码查找该用户信息
				result.setData(usersData);
			}else {
				result.setFlag(false);
				result.setMessage("修改失败");
				result.setData(usersData);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setFlag(false);
			result.setMessage("修改失败");
			result.setData(usersData);
		}
		return result;
	}
	
	
	
}
