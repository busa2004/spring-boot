package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CommentDao;
import com.douzone.jblog.vo.CommentVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class CommentService {
	
	@Autowired
	private CommentDao commentDao;

	public void insert(CommentVo commentVo) {
		commentDao.insert(commentVo);
	}

	public List<CommentVo> selectList(Long postNo) {
		return commentDao.select(postNo);
	}

	public void delete(Long no) {
		commentDao.delete(no);
	}
	
}
