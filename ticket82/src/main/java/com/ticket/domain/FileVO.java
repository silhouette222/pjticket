package com.ticket.domain;

public class FileVO {
	private int file_id;
	private String file_name;
	private String file_path;
	private int ttr_no;
	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public int getTtr_no() {
		return ttr_no;
	}
	public void setTtr_no(int ttr_no) {
		this.ttr_no = ttr_no;
	}
	public FileVO(){}
	public FileVO(int file_id, String file_name, String file_path, int ttr_no) {
		super();
		this.file_id = file_id;
		this.file_name = file_name;
		this.file_path = file_path;
		this.ttr_no = ttr_no;
	}
	
}
