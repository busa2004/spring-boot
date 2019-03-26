package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.CommentService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.CommentVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping({"/{id:(?!assets|uploads|user|admin|search|comment).*}"})
public class BlogController {
	@Autowired
	private UserService userService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	@Autowired
	private CommentService commentService;
	@RequestMapping( {"","/{categoryNo}","/{categoryNo}/{postNo}"} )
	public String blogMain(@PathVariable( "id" ) String id,
			@PathVariable Optional<Long> categoryNo,
			@PathVariable Optional<Long> postNo,
			Model model) {
		//ModelMap
		UserVo userVo = userService.select(id);
		BlogVo blogVo = blogService.select(userVo.getNo());
		List<CategoryVo> categoryList = categoryService.selectList(userVo.getNo());
		
		List<PostVo> postList;
		if(categoryNo.isPresent()) {
			 postList = postService.selectList(categoryNo.get());	
		}else {
			 postList = postService.selectList(categoryList.get(0).getNo());
		}
		
		PostVo post;
		 List<CommentVo> commentList=null;
		if(postNo.isPresent()) {
			 post=postService.select(postNo.get());
			 commentList=commentService.selectList(postNo.get());
		}else {
			 post=postService.select(postList.get(0).getNo());
		}
		
		
		System.out.println("postList : " + postList.get(0));
		System.out.println("post :"+post);
		model.addAttribute("commentList",commentList);
		model.addAttribute("id",id);
		model.addAttribute("blogVo",blogVo);
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("postList",postList);
		model.addAttribute("post",post);
		
		return "blog/blog-main";
	}
}
