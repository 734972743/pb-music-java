/**
 * 
 */
/**
 * @author ASUS
 *
 */
package com.pb.weixin.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pb.weixin.utils.SendEmail;
import com.pb.weixin.vo.Email;
@Component
public class Test{
	public static void main(String[] args) {
		String nowSr = "2020-05-14 00:00:00";

    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	try {
			Date now = format.parse(nowSr);
			
			Date now1 = new Date();
			
			System.out.println(now1.after(now));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//测试发邮件
//	@Scheduled(initialDelay=1000,fixedDelay=1000*100)
	public void testMail() {
		Email email = new Email();
		email.setAcceptAddress("734972743@qq.com");
		email.setSubject("PB音乐恭喜你注册成功");
		email.setBody("<strong>"+"张三"+"</strong>  你好:<br/>恭喜你成为PB音乐的用户</br>"
				+ "你的登录名为:<strong>"+"</strong>"
				+ "你的密码为:<strong>"+"</strong>请妥善保管好<br/>"
						+ "本网站网址为: <a href='www.panbang123.com'>www.panbang123.com</a>");
		email.setSendDate(new Date());
		
		SendEmail.sendEmail(email);
	}
	
	
}