package com.pb.weixin.vo;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;

public class StsTokenVO implements Serializable {

	
	 /**
     * 访问密钥标识
     */
	@Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    /**
     * 访问密钥
     */
	@Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;
    /**
     * 安全令牌
     */
    
    
    private String securityToken;
	public String getAccessKeyId() {
		return accessKeyId;
	}
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}
	public String getAccessKeySecret() {
		return accessKeySecret;
	}
	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}
	public String getSecurityToken() {
		return securityToken;
	}
	public void setSecurityToken(String securityToken) {
		this.securityToken = securityToken;
	}

    
}
