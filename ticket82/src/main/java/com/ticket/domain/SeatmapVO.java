package com.ticket.domain;

public class SeatmapVO {
	private int ttr_no;
	private String seatmap_name;
	private String seatmap_path;
	public int getTtr_no() {
		return ttr_no;
	}
	public void setTtr_no(int ttr_no) {
		this.ttr_no = ttr_no;
	}
	public String getSeatmap_name() {
		return seatmap_name;
	}
	public void setSeatmap_name(String seatmap_name) {
		this.seatmap_name = seatmap_name;
	}
	public String getSeatmap_path() {
		return seatmap_path;
	}
	public void setSeatmap_path(String seatmap_path) {
		this.seatmap_path = seatmap_path;
	}
	public SeatmapVO(){};
	public SeatmapVO(int ttr_no, String seatmap_name, String seatmap_path) {
		super();
		this.ttr_no = ttr_no;
		this.seatmap_name = seatmap_name;
		this.seatmap_path = seatmap_path;
	}
}
