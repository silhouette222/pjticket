package com.ticket.domain;

import java.util.Date;

public class QNAVO {

	private int qna_no;
	private String qna_title;
	private String qna_content;
	private Date qna_date;
	private String qna_pw;
	private String qna_pwin;
	private String qna_reply;
	private String am_id;
	private String mem_id;
	private String com_id;
	private int step;
	private int depth;
	private String ref;
	
	public QNAVO(){}

	public QNAVO(int qna_no, String qna_title, String qna_content,
			Date qna_date, String qna_pw, String qna_pwin, String qna_reply,
			String am_id, String mem_id, String com_id, int step, int depth,
			String ref) {
		super();
		this.qna_no = qna_no;
		this.qna_title = qna_title;
		this.qna_content = qna_content;
		this.qna_date = qna_date;
		this.qna_pw = qna_pw;
		this.qna_pwin = qna_pwin;
		this.qna_reply = qna_reply;
		this.am_id = am_id;
		this.mem_id = mem_id;
		this.com_id = com_id;
		this.step = step;
		this.depth = depth;
		this.ref = ref;
	}

	public int getQna_no() {
		return qna_no;
	}

	public void setQna_no(int qna_no) {
		this.qna_no = qna_no;
	}

	public String getQna_title() {
		return qna_title;
	}

	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}

	public String getQna_content() {
		return qna_content;
	}

	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}

	public Date getQna_date() {
		return qna_date;
	}

	public void setQna_date(Date qna_date) {
		this.qna_date = qna_date;
	}

	public String getQna_pw() {
		return qna_pw;
	}

	public void setQna_pw(String qna_pw) {
		this.qna_pw = qna_pw;
	}

	public String getQna_pwin() {
		return qna_pwin;
	}

	public void setQna_pwin(String qna_pwin) {
		this.qna_pwin = qna_pwin;
	}

	public String getQna_reply() {
		return qna_reply;
	}

	public void setQna_reply(String qna_reply) {
		this.qna_reply = qna_reply;
	}

	public String getAm_id() {
		return am_id;
	}

	public void setAm_id(String am_id) {
		this.am_id = am_id;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getCom_id() {
		return com_id;
	}

	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	@Override
	public String toString() {
		return "QNAVO [qna_no=" + qna_no + ", qna_title=" + qna_title
				+ ", qna_content=" + qna_content + ", qna_date=" + qna_date
				+ ", qna_pw=" + qna_pw + ", qna_pwin=" + qna_pwin
				+ ", qna_reply=" + qna_reply + ", am_id=" + am_id + ", mem_id="
				+ mem_id + ", com_id=" + com_id + ", step=" + step + ", depth="
				+ depth + ", ref=" + ref + "]";
	}
	
	
}
