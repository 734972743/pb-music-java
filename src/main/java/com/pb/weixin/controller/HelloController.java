package com.pb.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;


//@RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
//@RestController注解，相当于@Controller+@ResponseBody两个注解的结合，返回json数据不需要在方法前面加@ResponseBody注解了，但使用@RestController这个注解，就不能返回jsp,html页面，视图解析器无法解析jsp,html页面
//@RestController
@Controller
@RequestMapping("/jsp")
public class HelloController {

	@RequestMapping("/hello")
	public String hello(Model model){
		model.addAttribute("himsg","i am a hello test") ;
		model.addAttribute("test","今天天气真好") ;
		return "hello" ;
	}
}
