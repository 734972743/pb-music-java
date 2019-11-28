package com.pb.weixin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pb.weixin.vo.Singer;

public interface ISingerService {

	public int addSingerName(Singer singer);
	
	public List<Singer> getSingersBy(Singer singer);
}
