package com.pb.weixin.vo;

import java.util.Date;

public class Email {

	public String sendAddress;  //发送消息地址
	public String senderAlias;  //发送消息用户昵称
	public String acceptAddress;//接受地址  可以给多个收件人发送邮件，收件人之间用逗号分开
	public String subject;  //邮件标题，主体
	public String body; //消息内容
	public Date sendDate;  //发送日期
	public String tagName;  //控制台创建的标签
	
	
	
	public String getSendAddress() {
		return sendAddress;
	}
	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}
	public String getSenderAlias() {
		return senderAlias;
	}
	public void setSenderAlias(String senderAlias) {
		this.senderAlias = senderAlias;
	}
	public String getAcceptAddress() {
		return acceptAddress;
	}
	public void setAcceptAddress(String acceptAddress) {
		this.acceptAddress = acceptAddress;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	
	
	
}
