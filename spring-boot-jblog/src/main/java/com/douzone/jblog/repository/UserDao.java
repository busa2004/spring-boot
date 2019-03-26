package com.douzone.jblog.repository;


import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;


@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	public void insert(UserVo vo) {
		sqlSession.insert("user.insert",vo);
	}
	public UserVo select(String id, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put( "id", id );
		map.put( "password", password );
		return sqlSession.selectOne("user.selectByIdAndPassword",map);
	}
	public UserVo select(String id) {
		return sqlSession.selectOne("user.selectById",id);
	}
	public boolean selectOver(String id) {
		return sqlSession.selectOne("user.selectById",id);
	}
	public UserVo get(String id) {
		return sqlSession.selectOne("user.getById", id);
	}
	

	
	
//	public UserVo get( String email, String password )   {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put( "email", email );
//		map.put( "password", password );
//		return sqlSession.selectOne( "user.getByEmailAndPassword", map );
//	}
	

}
