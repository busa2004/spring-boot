package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class PostDao {
	@Autowired
	private SqlSession sqlSession;

	public void insert(PostVo postVo) {
		sqlSession.insert("post.insert",postVo);
	}

	public List<PostVo> selectList(Long no) {
		return sqlSession.selectList("post.selectList",no);
	}

	public PostVo select(Long no) {
		return sqlSession.selectOne("post.select",no);
	}

	public void delete(Long no) {
		sqlSession.delete("post.delete",no);
	}

	public void insert(CategoryVo categoryVo) {
		sqlSession.insert("post.insertDefault",categoryVo);
	}


	public void insert(UserVo vo) {
		sqlSession.insert("post.default",vo);
		
	}

	public List<PostVo> selectList(BlogVo blogVo) {
		return sqlSession.selectList("post.getSerachList",blogVo);
	}
}
