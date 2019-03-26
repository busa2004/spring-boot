package com.douzone.jblog.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;
@Controller
public class WriteController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private PostService postService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BlogService blogService;
	@RequestMapping( value="/{id}/admin/write" ,method=RequestMethod.GET)
	public String blogAdminWrite(@PathVariable( "id" ) String id,
			Model model) {
		UserVo userVo = userService.select(id);
		BlogVo blogVo = blogService.select(userVo.getNo());
		model.addAttribute("blogVo",blogVo);
		List<CategoryVo> list = categoryService.selectList(userVo.getNo());
		model.addAttribute("list",list);
		return "blog/blog-admin-write";
	}
	@RequestMapping( value="/{id}/admin/write" ,method=RequestMethod.POST)
	public String blogAdminWrite(@PathVariable( "id" ) String id,
			Model model, PostVo postVo,CategoryVo categoryVo) {
		
		UserVo userVo = userService.select(id);
		categoryVo.setUserNo(userVo.getNo());
		postVo.setCategoryNo(categoryService.selectNo(categoryVo));
		System.out.println();
		System.out.println("postVo : " + postVo);
		System.out.println();
		postService.insert(postVo);
		model.addAttribute("userVo",userVo);
		return "redirect:/{id}/admin/write";
	}
}
