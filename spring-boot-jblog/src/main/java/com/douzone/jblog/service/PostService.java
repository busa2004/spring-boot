package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogDao;
import com.douzone.jblog.repository.PostDao;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {
	@Autowired
	private PostDao postDao;

	public void insert(PostVo postVo) {
		postDao.insert(postVo);
	}

	public List<PostVo> selectList(Long no) {
		return postDao.selectList(no);
	}

	public PostVo select(Long no) {
		return postDao.select(no);
	}

	public List<PostVo> searchPost(BlogVo blogVo) {
		return postDao.selectList(blogVo);
	}
}
