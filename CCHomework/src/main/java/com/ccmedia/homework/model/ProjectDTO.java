package com.ccmedia.homework.model;

public class ProjectDTO {
	private int id;
	private String projectTitle;
	private String projectStart;
	private String projectEnd;
	private String createdBy;
	private String createdDate;
	private String isPost;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProjectTitle() {
		return projectTitle;
	}
	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	public String getProjectStart() {
		return projectStart;
	}
	public void setProjectStart(String projectStart) {
		this.projectStart = projectStart;
	}
	public String getProjectEnd() {
		return projectEnd;
	}
	public void setProjectEnd(String projectEnd) {
		this.projectEnd = projectEnd;
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
	
	public String getIsPost() {
		return isPost;
	}
	public void setIsPost(String isPost) {
		this.isPost = isPost;
	}
	@Override
	public String toString() {
		return "OrderStatusVO [id=" + id + ", projectTitle=" + projectTitle + ", projectStart=" + projectStart
				+ ", projectEnd=" + projectEnd + ", createdBy=" + createdBy + ", createdDate=" + createdDate + "]";
	}
}
