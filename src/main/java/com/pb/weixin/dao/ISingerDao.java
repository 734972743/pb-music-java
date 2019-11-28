package com.pb.weixin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pb.weixin.vo.Singer;

public interface ISingerDao {

	public int addSingerName(@Param("t") Singer singer);
	
	public List<Singer> getSingersBy(@Param("t") Singer singer);
	 
}
