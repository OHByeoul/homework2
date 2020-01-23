package com.ccmedia.homework.model;

import org.springframework.stereotype.Component;

@Component
public class NoticeDTO {
	private int id;
	private String title;
	private String createdBy;
	private String createdDate;
	private String content;
	private String isPost;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIsPost() {
		return isPost;
	}
	public void setIsPost(String isPost) {
		this.isPost = isPost;
	}
	
	@Override
	public String toString() {
		return "NoticeDTO [id=" + id + ", title=" + title + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", content=" + content + ", isPost=" + isPost + "]";
	}
}
