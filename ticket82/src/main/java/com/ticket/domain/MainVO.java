package com.ticket.domain;

public class MainVO {
	private String main_id;
	private int ttr_no;
	private String main_path;
	public String getMain_id() {
		return main_id;
	}
	public void setMain_id(String main_id) {
		this.main_id = main_id;
	}
	public int getTtr_no() {
		return ttr_no;
	}
	public void setTtr_no(int ttr_no) {
		this.ttr_no = ttr_no;
	}
	public String getMain_path() {
		return main_path;
	}
	public void setMain_path(String main_path) {
		this.main_path = main_path;
	}
	public MainVO(){};
	public MainVO(String main_id, int ttr_no, String main_path) {
		super();
		this.main_id = main_id;
		this.ttr_no = ttr_no;
		this.main_path = main_path;
	}
	@Override
	public String toString() {
		return "MainVO [main_id=" + main_id + ", ttr_no=" + ttr_no
				+ ", main_path=" + main_path + "]";
	}
}
