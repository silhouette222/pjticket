package com.ticket.domain;

import java.util.List;

public class ResPayVO {
	private List<ResVO> reslist;
	private String imp_uid;
	public List<ResVO> getReslist() {
		return reslist;
	}
	public void setReslist(List<ResVO> reslist) {
		this.reslist = reslist;
	}
	public String getImp_uid() {
		return imp_uid;
	}
	public void setImp_uid(String imp_uid) {
		this.imp_uid = imp_uid;
	}
	public ResPayVO(){}
	public ResPayVO(List<ResVO> reslist, String imp_uid) {
		super();
		this.reslist = reslist;
		this.imp_uid = imp_uid;
	}
	@Override
	public String toString() {
		return "ResPayVO [reslist=" + reslist + ", imp_uid=" + imp_uid + "]";
	}
}
