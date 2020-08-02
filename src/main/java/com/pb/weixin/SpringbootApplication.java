package com.pb.weixin;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;



//import com.pb.weixin.log.JuliaLog;




//启动类直接在根包下新建
@SpringBootApplication   //标记位一个springboot应用 的启动类
//@EnableSwagger2
@MapperScan("com.pb.weixin.dao")  //要扫描的dao包
@EnableScheduling //开启定时任务
public class SpringbootApplication extends SpringBootServletInitializer {

	
	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(SpringbootApplication.class);
	}

	public static void main(String[] args) {
		long  startTime = System.currentTimeMillis();
		SpringApplication.run(SpringbootApplication.class, args);
		
		long endTime = System.currentTimeMillis(); 
		long time = (endTime - startTime) / 1000 ;
		
		//JuliaLog.showLog();
		//System.out.println();
		
		System.out.println("          ---------  Spring Boot 已启动     ----------              ");
		System.out.println(" 一共花了:"+time+"秒");
	}
	
	
}
