package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.CommentService;
import com.douzone.jblog.vo.CommentVo;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("{id}/{categoryNo}/{postNo}/{userName}")
	public String commentInsert(@PathVariable("id") String id,CommentVo commentVo,
			@PathVariable("postNo") Long postNo,
			@PathVariable("categoryNo") Long categoryNo,
			@PathVariable("userName") String userName) {
		commentService.insert(commentVo);
		return "redirect:/"+id+"/"+categoryNo+"/"+postNo;
	}
	@RequestMapping("delete/{id}/{categoryNo}/{postNo}/{no}")
	public String delete(@PathVariable("id") String id,
			@PathVariable("postNo") Long postNo,
			@PathVariable("categoryNo") Long categoryNo,
			@PathVariable("no") Long no) {
		System.out.println("삭제~");
		commentService.delete(no);
		return "redirect:/"+id+"/"+categoryNo+"/"+postNo;
	}
}
