package com.douzone.df.model;

import javax.persistence.*;

@Entity
@Table(name = "eval")
public class Eval {

	@Column(name = "eval_no", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increament를 위해 지정
	private Long evalNo;

	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "eval_item_version_no", referencedColumnName = "item_version_no", nullable = false)
	private EvalItemVersion evalItemVersionNo;

	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "eval_item_score_no", referencedColumnName = "item_score_no", nullable = false)
	private EvalItemScore evalItemScoreno;

//	private UserTask userTaskNo;

	public Eval() {

	}

	public Eval(Long evalNo, EvalItemVersion evalItemVersionNo, EvalItemScore evalItemScoreno) {
		this.evalNo = evalNo;
		this.evalItemVersionNo = evalItemVersionNo;
		this.evalItemScoreno = evalItemScoreno;
		// userTask 추가
	}

	public Long getEvalNo() {
		return evalNo;
	}

	public void setEvalNo(Long evalNo) {
		this.evalNo = evalNo;
	}

	public EvalItemVersion getEvalItemVersionNo() {
		return evalItemVersionNo;
	}

	public void setEvalItemVersionNo(EvalItemVersion evalItemVersionNo) {
		this.evalItemVersionNo = evalItemVersionNo;
	}

	public EvalItemScore getEvalItemScoreno() {
		return evalItemScoreno;
	}

	public void setEvalItemScoreno(EvalItemScore evalItemScoreno) {
		this.evalItemScoreno = evalItemScoreno;
	}

//	public Long getUserTaskNo() {
//		return userTaskNo;
//	}
//
//	public void setUserTaskNo(Long userTaskNo) {
//		this.userTaskNo = userTaskNo;
//	}

}
