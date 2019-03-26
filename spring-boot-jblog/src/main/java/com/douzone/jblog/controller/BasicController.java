package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileuploadService;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;

@Controller
public class BasicController {
	@Autowired
	private UserService userService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private FileuploadService fileUploadService;
	@RequestMapping( value="/{id}/admin/basic", method=RequestMethod.GET)
	public String blogAdminBasic(@PathVariable( "id" ) String id,
			Model model) {
		UserVo userVo = userService.select(id);
		BlogVo blogVo = blogService.select(userVo.getNo());
		model.addAttribute("blogVo",blogVo);
		model.addAttribute("userVo",userVo);
		return "blog/blog-admin-basic";
	}
	@RequestMapping( value="/{id}/admin/basic", method=RequestMethod.POST)
	public String blogAdminBasicChange(@PathVariable( "id" ) String id,
			Model model,BlogVo blogVo,
			@RequestParam(value="logo-file") MultipartFile multipartFile) {
		String image = fileUploadService.restore(multipartFile);
		blogVo.setLogo(image);
		UserVo userVo = userService.select(id);
		blogVo.setUserNo(userVo.getNo());
		System.out.println(blogVo);
		blogService.update(blogVo);
		model.addAttribute("userVo",userVo);
		model.addAttribute("blogVo",blogVo);
		return "blog/blog-admin-basic";
	}
}
