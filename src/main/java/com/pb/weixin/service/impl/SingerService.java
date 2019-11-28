package com.pb.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pb.weixin.dao.ISingerDao;
import com.pb.weixin.service.ISingerService;
import com.pb.weixin.vo.Singer;

@Service
public class SingerService implements ISingerService {

	@Autowired
	private ISingerDao iSingerDao;
	
	@Override
	public int addSingerName(Singer singer) {
		// TODO Auto-generated method stub
		return iSingerDao.addSingerName(singer);
	}

	@Override
	public List<Singer> getSingersBy(Singer singer) {
		// TODO Auto-generated method stub
		return iSingerDao.getSingersBy(singer);
	}

}
