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
	private String[] files; //첨부파일
	private String[] seat_grd;//자리등급
	private String[] seat_id; //좌석 아이디
	private int[] seat_no; //자리번호
	private int[] seat_pri; //자리 가격
	private Date[] seat_date; //날짜
	private Date[] seat_time; //선택 시간
	
	
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
	public String[] getFiles() {
		return files;
	}
	public void setFiles(String[] files) {
		this.files = files;
	}
	public String[] getSeat_grd() {
		return seat_grd;
	}
	public void setSeat_grd(String[] seat_grd) {
		this.seat_grd = seat_grd;
	}
	public String[] getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(String[] seat_id) {
		this.seat_id = seat_id;
	}
	public int[] getSeat_no() {
		return seat_no;
	}
	public void setSeat_no(int[] seat_no) {
		this.seat_no = seat_no;
	}
	public int[] getSeat_pri() {
		return seat_pri;
	}
	public void setSeat_pri(int[] seat_pri) {
		this.seat_pri = seat_pri;
	}
	public Date[] getSeat_date() {
		return seat_date;
	}
	public void setSeat_date(Date[] seat_date) {
		this.seat_date = seat_date;
	}
	public Date[] getSeat_time() {
		return seat_time;
	}
	public void setSeat_time(Date[] seat_time) {
		this.seat_time = seat_time;
	}
	@Override
	public String toString() {
		return "MusicalVO [ttr_no=" + ttr_no + ", com_id=" + com_id
				+ ", ttr_cat=" + ttr_cat + ", ttr_title=" + ttr_title
				+ ", ttr_date=" + ttr_date + ", ttr_sdate=" + ttr_sdate
				+ ", ttr_edate=" + ttr_edate + ", ttr_place=" + ttr_place
				+ ", ttr_time=" + ttr_time + ", ttr_alert=" + ttr_alert
				+ ", ttr_content=" + ttr_content + ", thumb_name=" + thumb_name
				+ ", seatmap_name=" + seatmap_name + ", files="
				+ Arrays.toString(files) + ", seat_grd="
				+ Arrays.toString(seat_grd) + ", seat_no="
				+ Arrays.toString(seat_no) + ", seat_pri="
				+ Arrays.toString(seat_pri) + ", seat_date="
				+ Arrays.toString(seat_date) + ", seat_time="
				+ Arrays.toString(seat_time)+ ", seat_id="
				+ Arrays.toString(seat_id) + "]";
	}
	public MusicalVO(){}
	public MusicalVO(int ttr_no, String com_id, String ttr_cat,
			String ttr_title, Date ttr_date, Date ttr_sdate, Date ttr_edate,
			String ttr_place, String ttr_time, String ttr_alert,
			String ttr_content, String thumb_name, String seatmap_name,
			String[] files, String[] seat_grd, String[] seat_id,
			int[] seat_no, int[] seat_pri, Date[] seat_date,
			Date[] seat_time) {
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
		this.seat_grd = seat_grd;
		this.seat_no = seat_no;
		this.seat_pri = seat_pri;
		this.seat_date = seat_date;
		this.seat_time = seat_time;
		this.seat_id = seat_id;
	}
	
	
}













