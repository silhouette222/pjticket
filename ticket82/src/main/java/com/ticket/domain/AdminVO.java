package com.ticket.domain;

public class AdminVO {
	
	private String am_id;
	private String am_pw;
	
	public AdminVO(){}
	public AdminVO(String am_id, String am_pw) {
		super();
		this.am_id = am_id;
		this.am_pw = am_pw;
	}
	public String getAm_id() {
		return am_id;
	}
	public void setAm_id(String am_id) {
		this.am_id = am_id;
	}
	public String getAm_pw() {
		return am_pw;
	}
	public void setAm_pw(String am_pw) {
		this.am_pw = am_pw;
	}
	
	@Override
	public String toString() {
		return "AdminVO [am_id=" + am_id + ", am_pw=" + am_pw + "]";
	}
	
	
	

}
