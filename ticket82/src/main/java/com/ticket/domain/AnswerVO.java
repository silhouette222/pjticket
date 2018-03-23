package com.ticket.domain;

import java.util.Date;

public class AnswerVO {
	
	private int ano;
	private int qna_no;
	private String mem_id;
	private String am_id;
	private Date ans_date;
	private String ans_content;
	
	public AnswerVO(){}

	public AnswerVO(int ano, int qna_no, String mem_id, String am_id,
			Date ans_date, String ans_content) {
		super();
		this.ano = ano;
		this.qna_no = qna_no;
		this.mem_id = mem_id;
		this.am_id = am_id;
		this.ans_date = ans_date;
		this.ans_content = ans_content;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getQna_no() {
		return qna_no;
	}

	public void setQna_no(int qna_no) {
		this.qna_no = qna_no;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getAm_id() {
		return am_id;
	}

	public void setAm_id(String am_id) {
		this.am_id = am_id;
	}

	public Date getAns_date() {
		return ans_date;
	}

	public void setAns_date(Date ans_date) {
		this.ans_date = ans_date;
	}

	public String getAns_content() {
		return ans_content;
	}

	public void setAns_content(String ans_content) {
		this.ans_content = ans_content;
	}

	@Override
	public String toString() {
		return "AnswerVO [ano=" + ano + ", qna_no=" + qna_no + ", mem_id="
				+ mem_id + ", am_id=" + am_id + ", ans_date=" + ans_date
				+ ", ans_content=" + ans_content + "]";
	}
	

}
