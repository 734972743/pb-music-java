package com.pb.weixin.utils;


// 公共的返回结果类
public class BaseResult<T>  {

	public int code ;  // 返回结果状态码： 200为成功， 500 为失败
	public T data;    //返回的数据集
	public boolean flag;  //操作是否成功 ，成功为true， 失败为false
	public String message;    //返回的提示信息
	public Page page; //分页信息
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isFlag() {
		if(this.code == 200) {
			flag = true;
		}else if(this.code == 500) {
			flag = false;
		}
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	
	
	
	
	
}
