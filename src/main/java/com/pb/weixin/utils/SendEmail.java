package com.pb.weixin.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.pb.weixin.vo.Email;

@PropertySource("classpath:application.properties")
@Component
public class SendEmail {
	
	
	
	private static String accessKeyId;    //阿里云 AccessKeyId
	
	private static String accessKeySecret;  //阿里云 AccessKeySecret
	
	
	//由于是在静态方法里面使用@Value属性，所以要使用非静态的set方法类设置读取配置文件的值
	@Value("${aliyun.oss.accessKeyId}")
	public void setAccessKeyId(String accessKeyId) {
		SendEmail.accessKeyId = accessKeyId;
	}

	@Value("${aliyun.oss.accessKeySecret}")
	public void setAccessKeySecret(String accessKeySecret) {
		SendEmail.accessKeySecret = accessKeySecret;
	}
	

	public static void sendEmail(Email email) {
		 try {
	        	
	     		// 如果是除杭州region外的其它region（如新加坡、澳洲Region），需要将下面的"cn-hangzhou"替换为"ap-southeast-1"、或"ap-southeast-2"。
	     		DefaultProfile profile  = DefaultProfile.getProfile("cn-hangzhou",SendEmail.accessKeyId, SendEmail.accessKeySecret);   // "cn-hangzhou", "<your accessKey>", "<your accessSecret>
	     		DefaultAcsClient client = new DefaultAcsClient(profile);
	     		SingleSendMailRequest request = new SingleSendMailRequest();
	     		
	     		 request.setAccountName("admin@email.panbang123.com");    //控制台创建的发信地址
	              request.setFromAlias(email.getSenderAlias());   //发信人昵称
	              request.setAddressType(1);
	              request.setTagName(email.getTagName());   //控制台创建的标签
	              request.setReplyToAddress(true);  
	              request.setToAddress(email.getAcceptAddress());   //目标地址
	             
	              //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
	              //request.setToAddress("邮箱1,邮箱2");
	              request.setSubject(email.getSubject());    //邮件主题
	              
	              //如果采用byte[].toString的方式的话请确保最终转换成utf-8的格式再放入htmlbody和textbody，若编码不一致则会被当成垃圾邮件。
	              //注意：文本邮件的大小限制为3M，过大的文本会导致连接超时或413错误
	              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	              String bodyStr = ""
	              		+email.getBody()+  "<br/>"
	              		+ "当前时间为:北京时间 " + sdf.format(email.getSendDate())+"<br/>";
	              request.setHtmlBody(bodyStr);    //邮件正文
	              
	              //SDK 采用的是http协议的发信方式, 默认是GET方法，有一定的长度限制。
	              //若textBody、htmlBody或content的大小不确定，建议采用POST方式提交，避免出现uri is not valid异常
	              request.setMethod(MethodType.POST);
	              
	              //开启需要备案，0关闭，1开启
	              //request.setClickTrace("0");
	        	//如果调用成功，正常返回httpResponse；如果调用失败则抛出异常，需要在异常中捕获错误异常码；错误异常码请参考对应的API文档;
				SingleSendMailResponse httpResponse = client.getAcsResponse(request);
//				System.out.println("httpResponse:"+httpResponse);
//				System.out.println("邮件发送成功");
			} catch (ServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


	
}
