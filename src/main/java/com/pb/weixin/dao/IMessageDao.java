package com.pb.weixin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pb.weixin.vo.Message;

//JpaRepository 这个是jpa自带的对数据库的操作方法
//JpaRepository<实体类，id的类型>
public interface IMessageDao extends JpaRepository<Message, Integer>{

	public List<Message> getMessagesBySongId(@Param("songId")Integer songId);
}
