package com.pb.weixin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pb.weixin.utils.WeixinCheckUtil;


/**
 * 1.填写服务器配置
   2.验证服务器地址的有效性
   3.据接口文档实现业务逻辑，接收消息和事件
 * @author ASUS
 *
 */

@RestController
@RequestMapping(value="/weixinDispatchServlet")
public class WeixinDispatchServlet {

	
	/**
	 * 验证服务器地址的有效性
	 * @param signature  微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
	 * @param timestamp  时间戳
	 * @param nonce  随机数
	 * @param echostr  随机字符串
	 * @return  随机字符串
	 */
	@RequestMapping(value="/getDispatchServlet", method=RequestMethod.GET)
	public String getDispatchServlet(String signature,String timestamp,String nonce, String echostr) {
		if(WeixinCheckUtil.checkSignature(signature, timestamp, nonce)) {
			return null;
		}else {
			return echostr;
		}
	}
}
