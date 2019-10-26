package com.pb.weixin.service;

import java.util.List;

import com.pb.weixin.vo.Type;

public interface ITypeService extends IBaseService<Type>{

	/**
	 * 查询所有的音乐
	 * @return
	 */
	public List<Type> getTypesAll();
	
	/**
	 * 根据条件来查询音乐
	 * @return
	 */
	public List<Type> getTypesBy(Type type);
	
	/**
	 * 增加一首音乐
	 * @param song
	 * @return
	 */
	public int addType(Type type);
	
	/**
	 * 修改一首音乐
	 * @param song
	 * @return
	 */
	public int updateType(Type type);
	
	
	/**
	 * 删除一首音乐
	 * @param song
	 * @return
	 */
	public int deleteType(Type type);
}
