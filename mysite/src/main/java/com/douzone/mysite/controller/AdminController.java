package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.FileuploadService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.security.Auth;
import com.douzone.security.Auth.Role;

@Auth(Role.ADMIN)
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private SiteService siteService;
	@Autowired
	private FileuploadService fileUploadService;
	@RequestMapping({"", "/main"})
	public String main(Model model) {
		model.addAttribute("siteVo",siteService.getSiteInformation());
		return "admin/main";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/main/update" )	
	public String update(SiteVo siteVo,
			@RequestParam(value="upload-profile") MultipartFile multipartFile) {
		
		String profile = fileUploadService.restore(multipartFile);
		siteVo.setProfile(profile);
		siteService.updateSiteInformation(siteVo);
		return "redirect:/admin/main";
	}
	
}
