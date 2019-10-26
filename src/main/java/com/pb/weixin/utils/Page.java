package com.pb.weixin.utils;


//这个用于分页的工具类
public class Page {

	public int curPage ;  //这是当前页
	public int pageSize =1 ;  //每页多少行数据
	public int totalCount;   //总共多少记录数；
	public int pageCount;   //总共多少页；
	
	
	public int getCurPage() {
		return this.curPage ;
		
	}
	//设置当前页
	public void setCurPage(int curPage) {
		if(curPage < 0) {
			this.curPage = 0;
		}else if(curPage >this.pageCount){
			this.curPage = this.pageCount;
		}else {
			this.curPage = curPage;
		}
	}
	
	public int getPageSize() {
		
		
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageCount() {
		
		return totalCount/pageSize;  //总页数 = 总记录数/每页显示的条数
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	//初始化 ，获取总记录数
	public Page initRowCount(int totalCount) {
//        if (totalCount == 0) {
//            return null;
//        }
//        int ps = getPageSize();
//        if (ps == 0) {
//            ps = 10;
//        }
//        int pc = (totalCount + ps - 1) / ps;//
//        int cp = getCurPage();
//        cp = cp > pc ? pc : cp;
//       // cp = cp < 1 ? 1 : cp;
//        this.setPageCount(pc);
//        this.setCurPage(cp);
////        this.setEnd(cp * ps );
////        this.setStart((cp - 1) * ps);
//        this.totalCount = totalCount;
//        return this;
		
		int  cp = (this.curPage -1 ) * this.pageSize;
		this.curPage = cp;
		this.totalCount = totalCount;
		return this;
		
    }
	
	public Page() {}
	
	public Page(int curPage,int pageSize) {
		this.curPage = curPage;
		this.pageSize = pageSize;
	}
	
	public Page(int curPage, int pageSize, int totalCount, int pageCount) {
		
		this.curPage = curPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.pageCount = pageCount;
	}
	
	
	
	
	
	
}
