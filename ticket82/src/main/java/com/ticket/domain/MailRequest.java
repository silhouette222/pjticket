package com.ticket.domain;

import java.util.Arrays;

public class MailRequest {
	private String to[];
	private String from;
	private String title;
	private String content;
	public MailRequest(){};
	public MailRequest(String[] to, String from, String title, String content) {
		super();
		this.to = to;
		this.from = from;
		this.title = title;
		this.content = content;
	}
	public String[] getTo() {
		return to;
	}
	public void setTo(String[] to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "MailRequest [to=" + Arrays.toString(to) + ", from=" + from
				+ ", title=" + title + ", content=" + content + "]";
	}
}
