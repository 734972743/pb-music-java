package com.pb.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.pb.weixin.dao.CyricDao;
import com.pb.weixin.service.CyricService;
import com.pb.weixin.vo.Cyric;


@Service
public class CyricServiceImpl implements CyricService {
	
	@Autowired
	private CyricDao cyricDao;

	@Override
	public List<Cyric> getAllCyrics(Cyric cyric) {
		// TODO Auto-generated method stub
		return cyricDao.findAll();
	}

	@Override
	public List<Cyric> getAllCyricsByCyric(Cyric cyric) {
		// TODO Auto-generated method stub
		
		Example example = Example.of(cyric);
		return cyricDao.findAll(example);
	}

	@Override
	public void updateCyric(Cyric cyric) {
		// TODO Auto-generated method stub
		cyricDao.saveAndFlush(cyric);
	}

	@Override
	public void deleteCyric(Cyric cyric) {
		// TODO Auto-generated method stub
		cyricDao.delete(cyric);
	}

	@Override
	public void addCyric(Cyric cyric) {
		// TODO Auto-generated method stub
		cyricDao.save(cyric);
	}

}
