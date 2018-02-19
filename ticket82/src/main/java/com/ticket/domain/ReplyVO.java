package com.ticket.domain;

import java.util.Date;

public class ReplyVO {
	private int tr_rno;
	private String tr_content;
	private Date tr_date;
	private String mem_id;
	private int ttr_no;
	private int ttr_score;
	public int getTr_rno() {
		return tr_rno;
	}
	public void setTr_rno(int tr_rno) {
		this.tr_rno = tr_rno;
	}
	public String getTr_content() {
		return tr_content;
	}
	public void setTr_content(String tr_content) {
		this.tr_content = tr_content;
	}
	public Date getTr_date() {
		return tr_date;
	}
	public void setTr_date(Date tr_date) {
		this.tr_date = tr_date;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getTtr_no() {
		return ttr_no;
	}
	public void setTtr_no(int ttr_no) {
		this.ttr_no = ttr_no;
	}
	public int getTtr_score() {
		return ttr_score;
	}
	public void setTtr_score(int ttr_score) {
		this.ttr_score = ttr_score;
	}
	public ReplyVO() {}
	public ReplyVO(int tr_rno, String tr_content, Date tr_date, String mem_id,
			int ttr_no, int ttr_score) {
		super();
		this.tr_rno = tr_rno;
		this.tr_content = tr_content;
		this.tr_date = tr_date;
		this.mem_id = mem_id;
		this.ttr_no = ttr_no;
		this.ttr_score = ttr_score;
	}
	@Override
	public String toString() {
		return "ReplyVO [tr_rno=" + tr_rno + ", tr_content=" + tr_content
				+ ", tr_date=" + tr_date + ", mem_id=" + mem_id + ", ttr_no="
				+ ttr_no + ", ttr_score=" + ttr_score + "]";
	};
}
