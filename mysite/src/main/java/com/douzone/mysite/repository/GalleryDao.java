package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;

	public int update(GalleryVo galleryVo) {
		return sqlSession.insert( "gallery.insert", galleryVo );
	}

	public int insert(GalleryVo galleryVo) {
		return sqlSession.insert( "gallery.insert", galleryVo );
	}

	public List<GalleryVo> select() {
		return sqlSession.selectList("gallery.getList");
	}

	public int delete(Long no) {
		return sqlSession.delete( "gallery.delete", no );
	}
}
