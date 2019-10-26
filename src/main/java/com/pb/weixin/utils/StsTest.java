package com.pb.weixin.utils;

import org.springframework.beans.factory.annotation.Autowired;

import com.pb.weixin.service.IStsTokenService;

public class StsTest {

	@Autowired
	private static IStsTokenService iStsTokenService;
	
	public static void main(String[] args) {
		iStsTokenService.getStsToken();
	}
}
