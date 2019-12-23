package com.pb.weixin.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User {

	private Integer userId;   //用户编号
	private String loginId;   //用户账号
	private String password;   //用户密码
	private String userName;    //用户名称
	private Integer userSex  ;      //用户性别  0:男  1：女
	private String email;      //用户邮箱
	private String phone;       //用户手机
	private Integer userType;       //用户类型编号，默认0; 0:普通用户，1.vip，2.后台管理员admin
	private String sign;         //个人签名
	private String headSculptureUrl;    //用户头像图片链接地址
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	//@JSONField(format="yyyy/MM/dd")
	private Date registationDate;      //注册日期  类型TIMESTAMP（包括年月日，时分秒）
	private Integer userStateId ;      //用户状态信息编号（可拓展字段）默认0;0未激活状态（需要邮箱激活）1激活状态  2注销状态（账号废弃）
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserSex() {
		return userSex;
	}
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getHeadSculptureUrl() {
		return headSculptureUrl;
	}
	public void setHeadSculptureUrl(String headSculptureUrl) {
		this.headSculptureUrl = headSculptureUrl;
	}
	public Date getRegistationDate() {
		return registationDate;
	}
	public void setRegistationDate(Date registationDate) {
		this.registationDate = registationDate;
	}
	public Integer getUserStateId() {
		return userStateId;
	}
	public void setUserStateId(Integer userStateId) {
		this.userStateId = userStateId;
	}
       
	
	
}
