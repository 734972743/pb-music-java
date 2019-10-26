package com.pb.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pb.weixin.dao.ITypeDao;
import com.pb.weixin.service.ITypeService;
import com.pb.weixin.vo.Type;

@Service
public class TypeService extends BaseService<Type> implements ITypeService {
	
	@Autowired
	private ITypeDao typeDao;

	@Override
	public List<Type> getTypesAll() {
		// TODO Auto-generated method stub
		return typeDao.getTypesAll();
	}

	@Override
	public List<Type> getTypesBy(Type type) {
		// TODO Auto-generated method stub
		return typeDao.getTypesBy(type);
	}

	@Override
	public int addType(Type type) {
		// TODO Auto-generated method stub
		return typeDao.addType(type);
	}

	@Override
	public int updateType(Type type) {
		// TODO Auto-generated method stub
		return typeDao.updateType(type);
	}

	@Override
	public int deleteType(Type type) {
		// TODO Auto-generated method stub
		return typeDao.deleteType(type);
	}

	

}
