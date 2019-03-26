package com.douzone.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;


//@ReqeustMapping("/{id:(?!assets).*}")
@Controller
public class MainController {

	@Autowired
	BlogService blogService;
	
	@RequestMapping( { "/", "/main" } )
	public String index( Model model ,HttpSession session) {
		
		
		UserVo userVo =(UserVo) session.getAttribute("authUser");
		if(userVo == null) {
			return "main/index";
		}
		
		
		System.out.println(userVo.getNo());
		BlogVo blogVo = blogService.select(userVo.getNo());
		model.addAttribute("blogVo",blogVo);
		
		return "main/index";
	}
	
	
	
	

}