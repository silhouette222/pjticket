package com.ticket.domain;

import java.util.Date;


public class EventVO {
	
	private int et_no;
	private String et_title;
	private String et_content;
	private int et_count;
	private Date et_date;
	private String am_id;
	
	public EventVO(){}
	public EventVO(int et_no, String et_title, String et_content, int et_count,
			Date et_date, String am_id) {
		super();
		this.et_no = et_no;
		this.et_title = et_title;
		this.et_content = et_content;
		this.et_count = et_count;
		this.et_date = et_date;
		this.am_id = am_id;
	}

	public int getEt_no() {
		return et_no;
	}

	public void setEt_no(int et_no) {
		this.et_no = et_no;
	}

	public String getEt_title() {
		return et_title;
	}

	public void setEt_title(String et_title) {
		this.et_title = et_title;
	}

	public String getEt_content() {
		return et_content;
	}

	public void setEt_content(String et_content) {
		this.et_content = et_content;
	}

	public int getEt_count() {
		return et_count;
	}

	public void setEt_count(int et_count) {
		this.et_count = et_count;
	}

	public Date getEt_date() {
		return et_date;
	}

	public void setEt_date(Date et_date) {
		this.et_date = et_date;
	}

	public String getAm_id() {
		return am_id;
	}

	public void setAm_id(String am_id) {
		this.am_id = am_id;
	}

	@Override
	public String toString() {
		return "EventVO [et_no=" + et_no + ", et_title=" + et_title
				+ ", et_content=" + et_content + ", et_count=" + et_count
				+ ", et_date=" + et_date + ", am_id=" + am_id + "]";
	}
	
	
	

}
