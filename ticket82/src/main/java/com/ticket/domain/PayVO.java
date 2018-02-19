package com.ticket.domain;

public class PayVO {
	//type,title,pay,email,name,tel,addr,post
	private String title;
	private int pay;
	private String email;
	private String name;
	private String tel;
	private String addr;
	private String post;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public PayVO(String title, int pay, String email, String name, String tel,
			String addr, String post) {
		super();
		this.title = title;
		this.pay = pay;
		this.email = email;
		this.name = name;
		this.tel = tel;
		this.addr = addr;
		this.post = post;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PayVO(){}
}
	
