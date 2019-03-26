package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class CategoryDao {
	@Autowired
	private SqlSession sqlSession;

	public List<CategoryVo> selectList(Long no) {
		return sqlSession.selectList("category.select",no);
	}

	public int insert(CategoryVo categoryVo) {
		return sqlSession.insert("category.insert",categoryVo);
	}

	public CategoryVo select(CategoryVo categoryVo) {
		return sqlSession.selectOne("category.selectByNo",categoryVo.getNo());
	}

	public int delete(Long no) {
		return sqlSession.delete("category.delete",no);
	}

	public Long selectNo(CategoryVo categoryVo) {
		return sqlSession.selectOne("category.selectNo",categoryVo);
	}

	public List<CategoryVo> selectListReverse(Long no) {
		return sqlSession.selectList("category.selectReverse",no);
	}

	public void insert(UserVo vo) {
		sqlSession.insert("category.insertUserVo",vo);
	}

	public void insert(Long no) {
		sqlSession.insert("category.insert",no);
	}
}
