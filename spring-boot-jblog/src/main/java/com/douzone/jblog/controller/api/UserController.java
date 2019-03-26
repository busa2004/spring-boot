package com.douzone.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.dto.JSONResult;
import com.douzone.jblog.service.UserService;



@Controller("userApiController")
@RequestMapping("/user/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkid")
	public JSONResult checkEmail(@RequestParam(value="id", required=true, defaultValue="") String id) {
		boolean exist = userService.existId(id);
		System.out.println("exist : "+ exist);
		return JSONResult.success(exist);
	}
	@ResponseBody
	@RequestMapping("/id")
	public JSONResult id(@RequestParam(value="id", required=true, defaultValue="") String id) {
		return JSONResult.success(id);
	}
//	@ResponseBody
//	@RequestMapping("/logincheck")
//	public JSONResult checkEmail(UserVo uservo) {
//		if(null!=userService.loginCheck(uservo))
//		return JSONResult.success(true);
//		else 
//		return JSONResult.success(false);
//	}
}
