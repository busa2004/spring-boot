package com.douzone.jblog.vo;

public class BlogVo {
	private Long userNo;
	private String title;
	private String logo;
	private String keyword;
	private String which;
	private String userName;
	private String userId;
	
	
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getWhich() {
		return which;
	}
	public void setWhich(String which) {
		this.which = which;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	@Override
	public String toString() {
		return "BlogVo [userNo=" + userNo + ", title=" + title + ", logo=" + logo + "]";
	}
	
}
