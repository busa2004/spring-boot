package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class BlogDao {
	@Autowired
	private SqlSession sqlSession;

	public void insert(UserVo vo) {
		sqlSession.insert("blog.insert",vo);
	}

	public void update(BlogVo blogVo) {
		sqlSession.update("blog.update",blogVo);
		
	}

	public BlogVo select(Long no) {
		return sqlSession.selectOne("blog.select",no);
	}

	public List<BlogVo> selectTitle(BlogVo blogVo) {
		return sqlSession.selectList("blog.selectTitle",blogVo);
	}

	public List<BlogVo> selectUser(BlogVo blogVo) {
		return sqlSession.selectList("blog.selectUser",blogVo);
	}


}
