package com.douzone.df.payload;

import com.douzone.df.model.EvalItem;

public class EvalScoreRequest {
//	private Long itemNo;
//	private String content;
	private EvalItem item;
	private String score;
	
	public EvalScoreRequest() { }
	public EvalScoreRequest(EvalItem item, String score) {
		this.item = item;
		this.score = score;
	}
	public EvalItem getItem() {
		return item;
	}
	public void setItem(EvalItem item) {
		this.item = item;
	}
	
	//	public Long getItemNo() {
//		return itemNo;
//	}
//	public EvalScoreRequest(Long itemNo, String content, String score) {
//		this.itemNo = itemNo;
//		this.content = content;
//		this.score = score;
//	}
//	public void setItemNo(Long itemNo) {
//		this.itemNo = itemNo;
//	}
//	public String getContent() {
//		return content;
//	}
//	public void setContent(String content) {
//		this.content = content;
//	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "EvalScoreRequest [item=" + item + ", score=" + score + "]";
	}
	

//	@Override
//	public String toString() {
//		return "EvalScoreRequest [itemNo=" + itemNo + ", content=" + content + ", score=" + score + "]";
//	}
//	 
}
