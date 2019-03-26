package com.douzone.jblog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping( "/user" )
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
	@RequestMapping( value="/auth", method=RequestMethod.POST )
	public void auth() {
	}
	@RequestMapping( value="/logout", method=RequestMethod.GET )
	public void logout() {
	}
	@RequestMapping( value="/join", method=RequestMethod.GET )
	public String join() {
		return "user/join";
	}
	@RequestMapping( value="/login", method=RequestMethod.GET )
	public String login() {
		return "user/login";
	}
	@RequestMapping( value="/login/fail", method=RequestMethod.GET )
	public String loginFail(Model model) {
		model.addAttribute("result","fail");
		return "user/login";
	}
	@RequestMapping( value="/join", method=RequestMethod.POST )
	public String join(@ModelAttribute @Valid UserVo vo,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		userService.join(vo);
		categoryService.insert(vo);
		return "user/joinsuccess";
	}
	@RequestMapping( "/login/fail" )
	public String login(Model model) {
		model.addAttribute("result","fail");
		return "user/login";
	}
}
