package com.pb.weixin.service.impl;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import com.aliyun.oss.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.pb.weixin.service.IStsTokenService;
import com.pb.weixin.utils.HelloOSS2;
import com.pb.weixin.vo.StsTokenVO;

@PropertySource("classpath:application.properties")
public class StsTokenServiceImpl  {

	// PropertyConfigurator.configure("conf/log4j.properties");
	
	//LoggerFactory.getLogger(StsTokenServiceImpl.class);
	static Logger logger = Logger.getLogger(StsTokenServiceImpl.class);
	
    
    private static String accessKeyId  ;
    
    private static String accessKeySecret ;
    
    private static String roleArn;
    
    private static String roleSessionName ;
    
    
    @Value("${aliyun.oss.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
		StsTokenServiceImpl.accessKeyId = accessKeyId;
	}

    @Value("${aliyun.oss.accessKeySecret}")
	public void setAccessKeySecret(String accessKeySecret) {
		StsTokenServiceImpl.accessKeySecret = accessKeySecret;
	}
    
    @Value("${aliyun.sts.roleArn}")
	public static void setRoleArn(String roleArn) {
		StsTokenServiceImpl.roleArn = roleArn;
	}

    @Value("${aliyun.sts.roleSessionName}")
	public void setRoleSessionName(String roleSessionName) {
		StsTokenServiceImpl.roleSessionName = roleSessionName;
	}


	/**
     * token失效时间，单位秒(不设置默认1小时,这里设置5分钟)
     */
    private static final Long durationSeconds= 300L;
    private static final String ENDPOINT = "oss-cn-beijing.aliyuncs.com";

	
    /**
     * 获取STStoken接口
     * @param:
     * @return: StsTokenVO
     * @author: young
     * @date: 2018/12/4 13:59
     */
	public static StsTokenVO getStsToken() {
		StsTokenVO tokenVO = new StsTokenVO();
        try {
            // 添加endpoint（直接使用STS endpoint，前两个参数留空，无需添加region ID）
            DefaultProfile.addEndpoint("", "", "Sts", ENDPOINT);
            // 构造default profile（参数留空，无需添加region ID）
            IClientProfile profile = DefaultProfile.getProfile("", StsTokenServiceImpl.accessKeyId, StsTokenServiceImpl.accessKeySecret);
//            System.out.println("accessKeyId,"+StsTokenServiceImpl.accessKeyId);
            // 用profile构造client
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setMethod(MethodType.POST);
            request.setRoleArn(roleArn);
           // request.setRoleSessionName(roleSessionName);
            // request.setDurationSeconds(durationSeconds);
            // 针对该临时权限可以根据该属性赋予规则，格式为json，没有特殊要求，默认为空
            // request.setPolicy(policy); // Optional
            final AssumeRoleResponse response = client.getAcsResponse(request);
            AssumeRoleResponse.Credentials credentials = response.getCredentials();
            tokenVO.setAccessKeyId(credentials.getAccessKeyId());
            tokenVO.setAccessKeySecret(credentials.getAccessKeySecret());
            tokenVO.setSecurityToken(credentials.getSecurityToken());
            System.out.println("getSecurityToken,"+tokenVO.getSecurityToken());
            return tokenVO;
            
        } catch (Exception e) {
        	logger.error("获取阿里云STS临时授权权限失败，错误信息：" + e);
            return null;
        }

	}
	
	
	public static void main(String[] args) {
		getStsToken();
	}

}
