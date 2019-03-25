package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookDao;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	public List<GuestbookVo> getMessageList() {
		return guestbookDao.getList();
	}
	
	public List<GuestbookVo> getMessageList( Long startNo ) {
		return guestbookDao.getList( startNo );
	}	
	
	public boolean deleteMessage( GuestbookVo vo ){
		return 1 == guestbookDao.delete( vo );
	}
	
//	public boolean writeMessage( GuestbookVo vo ) {
//		int count = guestbookDao.insert(vo);
//		System.out.println("===================="+ vo.getNo());
//		return count == 1;
//	}
	public GuestbookVo writeMessage( GuestbookVo vo ) {
		guestbookDao.insert(vo);
		return guestbookDao.get(vo.getNo());
	}

	

}