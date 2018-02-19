package com.ticket.domain;

import java.sql.Date;


public class MemberVO {

	private String mem_id;
	private String mem_pw;
	private String mem_name;
	private String mem_gender;
	private Date mem_birth;
	private String mem_mail;
	private String mem_mobile;
	private String mem_addr;
	private Date mem_date;
	private String mem_status;
	private String enabled;
	
	public MemberVO(){}

	public MemberVO(String mem_id, String mem_pw, String mem_name,
			String mem_gender, Date mem_birth, String mem_mail,
			String mem_mobile, String mem_addr, Date mem_date,
			String mem_status, String enabled) {
		super();
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
		this.mem_name = mem_name;
		this.mem_gender = mem_gender;
		this.mem_birth = mem_birth;
		this.mem_mail = mem_mail;
		this.mem_mobile = mem_mobile;
		this.mem_addr = mem_addr;
		this.mem_date = mem_date;
		this.mem_status = mem_status;
		this.enabled = enabled;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_pw() {
		return mem_pw;
	}

	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_gender() {
		return mem_gender;
	}

	public void setMem_gender(String mem_gender) {
		this.mem_gender = mem_gender;
	}

	public Date getMem_birth() {
		return mem_birth;
	}

	public void setMem_birth(Date mem_birth) {
		this.mem_birth = mem_birth;
	}

	public String getMem_mail() {
		return mem_mail;
	}

	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}

	public String getMem_mobile() {
		return mem_mobile;
	}

	public void setMem_mobile(String mem_mobile) {
		this.mem_mobile = mem_mobile;
	}

	public String getMem_addr() {
		return mem_addr;
	}

	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}

	public Date getMem_date() {
		return mem_date;
	}

	public void setMem_date(Date mem_date) {
		this.mem_date = mem_date;
	}

	public String getMem_status() {
		return mem_status;
	}

	public void setMem_status(String mem_status) {
		this.mem_status = mem_status;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "MemberVO [mem_id=" + mem_id + ", mem_pw=" + mem_pw
				+ ", mem_name=" + mem_name + ", mem_gender=" + mem_gender
				+ ", mem_birth=" + mem_birth + ", mem_mail=" + mem_mail
				+ ", mem_mobile=" + mem_mobile + ", mem_addr=" + mem_addr
				+ ", mem_date=" + mem_date + ", mem_status=" + mem_status
				+ ", enabled=" + enabled + "]";
	}
	
	
	
	
	
}
