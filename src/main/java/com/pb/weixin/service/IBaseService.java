package com.pb.weixin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pb.weixin.dao.BaseDao;
import com.pb.weixin.utils.BaseResult;
import com.pb.weixin.utils.Page;

public interface IBaseService<T> {

	
    
    public Page queryTotalCount(Page page,T t);
    
    public BaseResult<List<T>> queryListPage(Page page,T t);

}
