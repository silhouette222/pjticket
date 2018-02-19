package com.ticket.domain;

import java.util.Arrays;

public class AdminCri extends Criteria{
	private String search;
	private String keyword;
	private String ord;
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getOrd() {
		return ord;
	}
	public void setOrd(String ord) {
		this.ord = ord;
	}
	public AdminCri(){}
	public AdminCri(String search, String keyword, String ord) {
		super();
		this.search = search;
		this.keyword = keyword;
		this.ord = ord;
	}
	@Override
	public String toString() {
		return "AdminCri [search=" + search + ", keyword=" + keyword + ", ord=" + ord + "]";
	}
}
