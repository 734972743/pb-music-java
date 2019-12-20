package com.pb.weixin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pb.weixin.dao.BaseDao;
import com.pb.weixin.exception.MyException;
import com.pb.weixin.utils.BaseResult;
import com.pb.weixin.utils.Page;
import com.pb.weixin.vo.Song;

public class BaseService<T> {

	@Autowired
    private BaseDao<T> baseDao;
    
    public Page queryTotalCount(Page page,T t){
    	int totalCount =  baseDao.queryTotalCount(t);
    	return page.initRowCount(totalCount);
    //    return page.initRowCount(baseDao.queryTotalCount());
    //	return page;
    }
    
    
    
    //上面的方法就不需要调用，只需要调用下面的方法
    public BaseResult<List<T>> queryListPage(Page page,T t){
    	if(t == null) {
			throw new MyException("参数为空");
		}
    	
    	BaseResult<List<T>> result = new BaseResult<List<T>>();
        page = this.queryTotalCount(page,t);
        
        //int totalCount = 0;
        result.setPage(page);
        if(page == null) {
        	return result;	
        }
       
        List<T> data  = new ArrayList<T>();
         try {
        	 data =baseDao.queryListPage(page.getCurPage(), page.getPageSize(), t);
        	// totalCount = data.size();
//        	 page.initRowCount(totalCount);
//        	 result.setPage(page);
        	 result.setData(data);
             result.setCode(200);
             result.setFlag(true);
             return result;
         } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 result.setData(null);
             result.setCode(500);
             result.setFlag(false);
//             page.setTotalCount(0);
//             result.setPage(page);
             return result;
		}
    }
}
