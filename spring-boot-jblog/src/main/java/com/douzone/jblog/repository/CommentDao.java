package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CommentVo;
import com.douzone.jblog.vo.PostVo;

@Repository
public class CommentDao {
	@Autowired
	SqlSession sqlSession;

	public void insert(CommentVo commentVo) {
		sqlSession.insert("comment.insert",commentVo);
	}

	public List<CommentVo> select(Long postNo) {
		return sqlSession.selectList("comment.selectList",postNo);
	}

	public void delete(Long no) {
		sqlSession.delete("comment.delete",no);	
	}
	
	
}
