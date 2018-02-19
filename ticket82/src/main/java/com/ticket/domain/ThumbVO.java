package com.ticket.domain;

public class ThumbVO {
	private int ttr_no;
	private String thumb_name;
	private String thumb_path;
	public int getTtr_no() {
		return ttr_no;
	}
	public void setTtr_no(int ttr_no) {
		this.ttr_no = ttr_no;
	}
	public String getThumb_name() {
		return thumb_name;
	}
	public void setThumb_name(String thumb_name) {
		this.thumb_name = thumb_name;
	}
	public String getThumb_path() {
		return thumb_path;
	}
	public void setThumb_path(String thumb_path) {
		this.thumb_path = thumb_path;
	}
	public ThumbVO(){};
	public ThumbVO(int ttr_no, String thumb_name, String thumb_path) {
		super();
		this.ttr_no = ttr_no;
		this.thumb_name = thumb_name;
		this.thumb_path = thumb_path;
	}
}
