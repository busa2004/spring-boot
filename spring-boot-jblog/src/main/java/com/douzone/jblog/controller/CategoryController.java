package com.douzone.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.dto.JSONResult;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Controller
public class CategoryController {
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BlogService blogService;
	@RequestMapping( "/{id}/admin/category" )
	public String blogAdminCategory(@PathVariable( "id" ) String id,
			Model model) {
		UserVo userVo = userService.select(id);
		BlogVo blogVo = blogService.select(userVo.getNo());
		model.addAttribute("blogVo",blogVo);
//		List<CategoryVo> list = categoryService.selectList(userVo.getNo());
//		model.addAttribute("size",list.size());
//		model.addAttribute("userVo",userVo);
//		model.addAttribute("list",list);
		
		return "blog/blog-admin-category";
	}
	
	@ResponseBody
	@RequestMapping("/{id}/admin/category/list")
	public JSONResult listMessage(@PathVariable( "id" ) String id) {
		UserVo userVo = userService.select(id);
		List<CategoryVo> list = categoryService.selectListReverse(userVo.getNo());
		return JSONResult.success(list);
	}
	
	@ResponseBody
	@RequestMapping("/{id}/admin/category/delete")
	public JSONResult deleteMessage(@PathVariable( "id" ) String id,
			Long no) {
		categoryService.delete(no);
		return JSONResult.success(no);
	}
	@ResponseBody
	@RequestMapping("/{id}/admin/category/insert")
	public JSONResult writeMessage(@PathVariable( "id" ) String id,
			CategoryVo categoryVo) {
		return JSONResult.success(categoryService.insert(categoryVo));
	}
	
}
