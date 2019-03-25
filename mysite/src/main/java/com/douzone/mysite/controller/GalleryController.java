package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.FileuploadService;
import com.douzone.mysite.service.GalleryService;
import com.douzone.mysite.vo.GalleryVo;
import com.douzone.security.Auth;
import com.douzone.security.Auth.Role;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	private FileuploadService fileUploadService;
	@Autowired
	private GalleryService galleryService;
	@RequestMapping("")
	public String index(Model model) {
		List<GalleryVo> list = galleryService.getGalleryList();
		model.addAttribute("list",list);
		return "gallery/index";
	}
	
	@Auth(Role.ADMIN)
	@RequestMapping("/upload" )	
	public String upload(GalleryVo galleryVo,
			@RequestParam(value="upload-image") MultipartFile multipartFile) {
		
		String image = fileUploadService.restore(multipartFile);
		if(image==null||image.equals(""))
		return "gallery/index";
		galleryVo.setImage(image);
		galleryService.insert(galleryVo);
		return "redirect:/gallery";
	}
	
	@Auth(Role.ADMIN)
	@RequestMapping("/delete/{no}" )	
	public String delete(@PathVariable Long no) {
		galleryService.delete(no);
		return "redirect:/gallery";
	}
	
}
