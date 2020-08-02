package com.pb.weixin.utils;

import java.security.MessageDigest;
import java.util.Arrays;

public class WeixinCheckUtil {

	
	public static final String token ="pb-music";  //开发者自定义的token
	
	public static boolean checkSignature(String signature,String timestamp, String nonce) {
		//1.定义数组存放token, timestamp, nonce
		String[] arr = {token, timestamp,nonce}; 
		
		//2.对数组进行排序
		Arrays.sort(arr);
		
		//3.生成字符串
		StringBuffer sb = new StringBuffer();
		for(String s : arr) {
			sb.append(s);
		}
		
		//4.sha1加密，网上具有现成代码
		String temp = getSha1(sb.toString());
		
		return temp.equals(signature);   //跟签名进行比较
	}

	private static String getSha1(String str) {
		// TODO Auto-generated method stub
		if(str == null  || str.length() == 0) {
			return null;
		}
		
		char hexDigts[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("utf-8"));
			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j*2];
			int k = 0;
			for(int i=0; i<j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigts[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigts[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
