package com.ticket.domain;

public class MainVO {
	
	private String main_path;
	private String main_use;
	private int ttr_no;
	private String main_name;
	
	public MainVO(){}
	public MainVO(String main_path, String main_use, int ttr_no,
			String main_name) {
		super();
		this.main_path = main_path;
		this.main_use = main_use;
		this.ttr_no = ttr_no;
		this.main_name = main_name;
	}
	
	public String getMain_path() {
		return main_path;
	}
	public void setMain_path(String main_path) {
		this.main_path = main_path;
	}
	public String getMain_use() {
		return main_use;
	}
	public void setMain_use(String main_use) {
		this.main_use = main_use;
	}
	public int getTtr_no() {
		return ttr_no;
	}
	public void setTtr_no(int ttr_no) {
		this.ttr_no = ttr_no;
	}
	public String getMain_name() {
		return main_name;
	}
	public void setMain_name(String main_name) {
		this.main_name = main_name;
	}
	
	@Override
	public String toString() {
		return "MainVO [main_path=" + main_path + ", main_use=" + main_use
				+ ", ttr_no=" + ttr_no + ", main_name=" + main_name + "]";
	}
}
