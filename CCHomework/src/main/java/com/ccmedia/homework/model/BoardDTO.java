package com.ccmedia.homework.model;

import org.springframework.stereotype.Component;

@Component
public class BoardDTO {
	private int id;
	private String sort;
	private String title;
	private String content;
	private String createdBy;
	private String YMD;
	private String views;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getYMD() {
		return YMD;
	}
	public void setYMD(String yMD) {
		YMD = yMD;
	}
	public String getViews() {
		return views;
	}
	public void setViews(String view) {
		this.views = view;
	}
	
	@Override
	public String toString() {
		return "Board [id=" + id + ", sort=" + sort + ", title=" + title + ", content=" + content + ", createdBy="
				+ createdBy + ", YMD=" + YMD + ", views=" + views + "]";
	}
	
}
