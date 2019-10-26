package com.pb.weixin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

//这个DAO是用于分页的
public interface BaseDao<T> {
	
	public int queryTotalCount(@Param("t") T t);
	public List<T> queryListPage(@Param("curPage") Integer curPage,@Param("pageSize")  Integer pageSize, @Param("t") T t);

}
