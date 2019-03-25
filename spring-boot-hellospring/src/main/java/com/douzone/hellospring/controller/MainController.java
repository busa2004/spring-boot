package com.douzone.hellospring.controller;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	@ResponseBody
	//메소드 단독 메핑
	@RequestMapping({"/main",""})
	public String main() {
		
		return "MainController:main()";
	}
	
	@ResponseBody
	//메소드 단독 메핑
	@RequestMapping("/main/a/b/c/d")
	public String main2() {
		
		return "MainController:main2()";
	}
	
	//기술이 침투되어있다
	//사용해서는 안된다.
	@ResponseBody
	@RequestMapping("/main3")
	public String main3(HttpServletRequest request,Writer out) {
		String name = request.getParameter("n");
		return "MainController:main3()";
	}
}
