package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogDao;
import com.douzone.jblog.vo.BlogVo;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;

	public void update(BlogVo blogVo) {
		blogDao.update(blogVo);
	}

	public BlogVo select(Long no) {
		return blogDao.select(no);
	}

	public List<BlogVo> searchTitle(BlogVo blogVo) {
		return  blogDao.selectTitle(blogVo);
	}

	public List<BlogVo> searchUser(BlogVo blogVo) {
		return blogDao.selectUser(blogVo);
	}
}
