package com.pb.weixin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pb.weixin.vo.Type;

public interface ITypeDao extends BaseDao<Type> {

	/**
	 * 查询所有的音乐
	 * @return
	 */
	public List<Type> getTypesAll();
	
	/**
	 * 根据条件来查询音乐
	 * @return
	 */
	public List<Type> getTypesBy(@Param("t") Type type);
	
	/**
	 * 增加一首音乐
	 * @param song
	 * @return
	 */
	public int addType(@Param("t") Type type);
	
	/**
	 * 修改一首音乐
	 * @param song
	 * @return
	 */
	public int updateType(@Param("t") Type type);
	
	
	/**
	 * 删除一首音乐
	 * @param song
	 * @return
	 */
	public int deleteType(Type type);
}
