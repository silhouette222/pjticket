package com.ticket.domain;

import java.sql.Date;


public class CompanyVO {

	private int com_no;
	private String com_id;
	private String com_pw;
	private String com_name;
	private String com_dname;
	private int com_mobile;
	private String com_mail;
	private String com_addr;
	private int enabled;
	private Date com_date;
	
	public CompanyVO(){}
	public CompanyVO(int com_no, String com_id, String com_pw, String com_name,
			String com_dname, int com_mobile, String com_mail, String com_addr,
			int enabled, Date com_date) {
		super();
		this.com_no = com_no;
		this.com_id = com_id;
		this.com_pw = com_pw;
		this.com_name = com_name;
		this.com_dname = com_dname;
		this.com_mobile = com_mobile;
		this.com_mail = com_mail;
		this.com_addr = com_addr;
		this.enabled = enabled;
		this.com_date = com_date;
	}
	
	public int getCom_no() {
		return com_no;
	}
	public void setCom_no(int com_no) {
		this.com_no = com_no;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getCom_pw() {
		return com_pw;
	}
	public void setCom_pw(String com_pw) {
		this.com_pw = com_pw;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getCom_dname() {
		return com_dname;
	}
	public void setCom_dname(String com_dname) {
		this.com_dname = com_dname;
	}
	public int getCom_mobile() {
		return com_mobile;
	}
	public void setCom_mobile(int com_mobile) {
		this.com_mobile = com_mobile;
	}
	public String getCom_mail() {
		return com_mail;
	}
	public void setCom_mail(String com_mail) {
		this.com_mail = com_mail;
	}
	public String getCom_addr() {
		return com_addr;
	}
	public void setCom_addr(String com_addr) {
		this.com_addr = com_addr;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public Date getCom_date() {
		return com_date;
	}
	public void setCom_date(Date com_date) {
		this.com_date = com_date;
	}
	
	@Override
	public String toString() {
		return "CompanyVO [com_no=" + com_no + ", com_id=" + com_id
				+ ", com_pw=" + com_pw + ", com_name=" + com_name
				+ ", com_dname=" + com_dname + ", com_mobile=" + com_mobile
				+ ", com_mail=" + com_mail + ", com_addr=" + com_addr
				+ ", enabled=" + enabled + ", com_date=" + com_date + "]";
	}
	
}
