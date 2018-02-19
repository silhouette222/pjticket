package com.ticket.domain;

import java.util.Arrays;
import java.util.Date;

public class MusicalVO {

	private int ttr_no;//글번호
	private String com_id;//기업아이디
	private String ttr_cat;//전시종류
	private String ttr_title;//제목
	private Date ttr_date;//등록일
	private Date ttr_sdate;//행사시작일
	private Date ttr_edate;//행사종료일
	private String ttr_place;//행사장소
	private String ttr_time;//시간정보
	private String ttr_alert;//알림
	private String ttr_content;//상세설명
	private String thumb_name;//썸네일
	private String seatmap_name;//자리	
	private String[] files;
	
	public MusicalVO(){}
	public MusicalVO(int ttr_no, String com_id, String ttr_cat,
			String ttr_title, Date ttr_date, Date ttr_sdate, Date ttr_edate,
			String ttr_place, String ttr_time, String ttr_alert,
			String ttr_content,String thumb_name, String seatmap_name, String[] files) {
		super();
		this.ttr_no = ttr_no;
		this.com_id = com_id;
		this.ttr_cat = ttr_cat;
		this.ttr_title = ttr_title;
		this.ttr_date = ttr_date;
		this.ttr_sdate = ttr_sdate;
		this.ttr_edate = ttr_edate;
		this.ttr_place = ttr_place;
		this.ttr_time = ttr_time;
		this.ttr_alert = ttr_alert;
		this.ttr_content = ttr_content;
		this.thumb_name = thumb_name;
		this.seatmap_name = seatmap_name;
		this.files = files;
	}
	public int getTtr_no() {
		return ttr_no;
	}

	public void setTtr_no(int ttr_no) {
		this.ttr_no = ttr_no;
	}

	public String getCom_id() {
		return com_id;
	}

	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}

	public String getTtr_cat() {
		return ttr_cat;
	}

	public void setTtr_cat(String ttr_cat) {
		this.ttr_cat = ttr_cat;
	}

	public String getTtr_title() {
		return ttr_title;
	}

	public void setTtr_title(String ttr_title) {
		this.ttr_title = ttr_title;
	}

	public Date getTtr_date() {
		return ttr_date;
	}

	public void setTtr_date(Date ttr_date) {
		this.ttr_date = ttr_date;
	}

	public Date getTtr_sdate() {
		return ttr_sdate;
	}

	public void setTtr_sdate(Date ttr_sdate) {
		this.ttr_sdate = ttr_sdate;
	}

	public Date getTtr_edate() {
		return ttr_edate;
	}

	public void setTtr_edate(Date ttr_edate) {
		this.ttr_edate = ttr_edate;
	}

	public String getTtr_place() {
		return ttr_place;
	}

	public void setTtr_place(String ttr_place) {
		this.ttr_place = ttr_place;
	}

	public String getTtr_time() {
		return ttr_time;
	}

	public void setTtr_time(String ttr_time) {
		this.ttr_time = ttr_time;
	}

	public String getTtr_alert() {
		return ttr_alert;
	}

	public void setTtr_alert(String ttr_alert) {
		this.ttr_alert = ttr_alert;
	}

	public String getTtr_content() {
		return ttr_content;
	}

	public void setTtr_content(String ttr_content) {
		this.ttr_content = ttr_content;
	}

	public String[] getFiles() {
		return files;
	}

	public String getThumb_name() {
		return thumb_name;
	}
	public void setThumb_name(String thumb_name) {
		this.thumb_name = thumb_name;
	}
	public String getSeatmap_name() {
		return seatmap_name;
	}
	public void setSeatmap_name(String seatmap_name) {
		this.seatmap_name = seatmap_name;
	}
	public void setFiles(String[] files) {
		this.files = files;
	}

	

	@Override
	public String toString() {
		return "MusicalVO [ttr_no=" + ttr_no + ", com_id=" + com_id
				+ ", ttr_cat=" + ttr_cat + ", ttr_title=" + ttr_title
				+ ", ttr_date=" + ttr_date + ", ttr_sdate=" + ttr_sdate
				+ ", ttr_edate=" + ttr_edate + ", ttr_place=" + ttr_place
				+ ", ttr_time=" + ttr_time + ", ttr_alert=" + ttr_alert
				+ ", ttr_content=" + ttr_content +  ", thumb_name=" + thumb_name
				+ ", seatmap_name=" + seatmap_name +", files="
				+ Arrays.toString(files) + "]";
	}
	
	
}