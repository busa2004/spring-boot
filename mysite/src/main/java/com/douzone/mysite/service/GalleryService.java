package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GalleryDao;
import com.douzone.mysite.repository.SiteDao;
import com.douzone.mysite.vo.GalleryVo;

@Service
public class GalleryService {
	@Autowired
	private GalleryDao galleryDao;
	public boolean updateSiteInformation(GalleryVo galleryVo) {
		return galleryDao.update(galleryVo) == 1;
	}
	public boolean insert(GalleryVo galleryVo) {
		return galleryDao.insert(galleryVo) == 1;
	}
	public List<GalleryVo> getGalleryList() {
		return galleryDao.select();
	}
	public boolean delete(Long no) {
		return galleryDao.delete(no) == 1;
	}

}
