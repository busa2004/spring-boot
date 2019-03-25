package com.douzone.emaillist.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.emaillist.dao.EmaillistDao;
import com.douzone.emaillist.vo.EmaillistVo;

@Controller
public class EmaillistController {
	
	@Autowired // 자동으로 DI 하는 변수
	private EmaillistDao emaillistDao;
	
	/*@RequestMapping("")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", emaillistDao.getList());
		mav.setViewName("/WEB-INF/views/list.jsp");
		return mav;
	}*/
	
	@RequestMapping("")
	public String list(Model model) {
		model.addAttribute("list",emaillistDao.getList());
		return "/WEB-INF/views/list.jsp";
	}
	
	@RequestMapping("/form")
	public String form() {
		return "/WEB-INF/views/form.jsp";
	}
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(EmaillistVo emaillistVo) {
		emaillistDao.insert(emaillistVo);
		return "redirect:/";
	}
	
}
