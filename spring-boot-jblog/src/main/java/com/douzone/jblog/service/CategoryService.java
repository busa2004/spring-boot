package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryDao;
import com.douzone.jblog.repository.PostDao;
import com.douzone.jblog.repository.UserDao;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PostDao postDao;
	public List<CategoryVo> selectList(Long no) {
		return categoryDao.selectList(no);
	}

	public CategoryVo insert(CategoryVo categoryVo) {
		 categoryDao.insert(categoryVo);
		 postDao.insert(categoryVo);
		 CategoryVo vo=categoryDao.select(categoryVo);
		 System.out.println(vo);
		 return vo;
	}

	public int delete(Long no) {
		postDao.delete(no);
		return categoryDao.delete(no);
	}

	public Long selectNo(CategoryVo categoryVo) {
		return categoryDao.selectNo(categoryVo);
	}

	public List<CategoryVo> selectListReverse(Long no) {
		return categoryDao.selectListReverse(no);
	}


	public void insert(UserVo vo) {
		categoryDao.insert(vo);
		postDao.insert(vo);
	}

	
}
