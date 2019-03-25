package com.douzone.mysite.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller
@RequestMapping( "/guestbook" )
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping( "" )
	public String index( Model model ,GuestbookVo guestbookVo){
		List<GuestbookVo> list = guestbookService.getMessageList();
		model.addAttribute( "list", list );
		return "guestbook/index";
	}
	
	@RequestMapping( value="/delete/{no}", method=RequestMethod.GET )
	public String deleteform( @PathVariable( "no" ) Long no, Model model ){
		model.addAttribute( "no", no );
		return "guestbook/delete";
	}
	
	@RequestMapping( value="/delete", method=RequestMethod.POST )
	public String delete( @ModelAttribute GuestbookVo guestbookVo ){
		guestbookService.deleteMessage( guestbookVo );
		return "redirect:/guestbook";
	}
	
	@RequestMapping( value="/add", method=RequestMethod.POST )
	public String add( @ModelAttribute @Valid GuestbookVo guestbookVo ,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			List<GuestbookVo> list = guestbookService.getMessageList();
			model.addAttribute( "list", list );
			model.addAllAttributes(result.getModel());
			return "guestbook/index";
		}
		guestbookService.writeMessage( guestbookVo );
		return "redirect:/guestbook";
	}
	
	@RequestMapping( "/ajax" )
	public String ajax() {
		return "guestbook/index-ajax";
	}
}