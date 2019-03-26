package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogDao;
import com.douzone.jblog.repository.CategoryDao;
import com.douzone.jblog.repository.PostDao;
import com.douzone.jblog.repository.UserDao;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {

	
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	
	
	public void join(UserVo vo) {
		userDao.insert(vo);
		System.out.println(vo);
		blogDao.insert(vo);
	}

	public UserVo getUser(String id, String password) {
		return userDao.select(id,password);
	}

	public UserVo select(String id) {
		return userDao.select(id);
	}

	public boolean existId(String id) {
		UserVo userVo = userDao.get( id );
		return userVo != null;
	}
	
	
//	public boolean existEmail( String email ) {
//		UserVo userVo = userDao.get( email );
//		return userVo != null;
//	}
//	
//	public void join( UserVo userVo ) {
//		//1.DB에 사용정보 저장
//		userDao.insert( userVo );
//		
//		//2. 인증 메일 보내기
//	}
//	
//	public UserVo getUser( String email, String password ) {
//		return userDao.get( email, password );
//	}
//	
//	public UserVo getUser( Long no ) {
//		return userDao.get( no );
//	}
//	
//	public boolean modifyUser( UserVo userVo ) {
//		return userDao.update( userVo ) == 1;
//	}
//
//	public UserVo loginCheck(UserVo userVo) {
//		return userDao.get( userVo );
//	}
	
}
