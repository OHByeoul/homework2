package com.ccmedia.homework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ccmedia.homework.model.BoardDTO;
import com.ccmedia.homework.model.CommentDTO;
import com.ccmedia.homework.model.NoticeDTO;
import com.ccmedia.homework.model.ProjectDTO;

public class Util {
	private static SimpleDateFormat originFormat = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static void setDateFormatList(List<BoardDTO> boards){
		try {
				for(BoardDTO board : boards) {
					Date originDate = originFormat.parse(board.getYMD());
					String changedFormat = newFormat.format(originDate);
					board.setYMD(changedFormat);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
	}
	
	public static void setNoticeFormatList(List<NoticeDTO> notices){
		try {
				for(NoticeDTO notice : notices) {
					Date originDate = originFormat.parse(notice.getCreatedDate());
					String changedFormat = newFormat.format(originDate);
					notice.setCreatedDate(changedFormat);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
	}
	
	public static void setProjectFormatList(List<ProjectDTO> projects){
		try {
				for(ProjectDTO project : projects) {
					Date originDate = originFormat.parse(project.getCreatedDate());
					String changedFormat = newFormat.format(originDate);
					project.setCreatedDate(changedFormat);
					
					Date originDate2 = originFormat.parse(project.getProjectStart());
					String changedFormat2 = newFormat.format(originDate2);
					project.setProjectStart(changedFormat2);
					
					Date originDate3 = originFormat.parse(project.getProjectEnd());
					String changedFormat3 = newFormat.format(originDate3);
					project.setProjectEnd(changedFormat3);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
	}
	
	public static void setCommentDateFormatList(List<CommentDTO> comments){
		try {
				for(CommentDTO comment : comments) {
					Date originDate = originFormat.parse(comment.getYMD());
					String changedFormat = newFormat.format(originDate);
					comment.setYMD(changedFormat);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
	}
	public static void setDateFormat(BoardDTO board){
		try {
				Date originDate = originFormat.parse(board.getYMD());
				String changedFormat = newFormat.format(originDate);
				board.setYMD(changedFormat);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	}
	
	public static void setDateFormat(NoticeDTO notice){
		try {
				Date originDate = originFormat.parse(notice.getCreatedDate());
				String changedFormat = newFormat.format(originDate);
				notice.setCreatedDate(changedFormat);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	}
	
	public static void setDateFormat(ProjectDTO project){
		try {
				Date originDate = originFormat.parse(project.getCreatedDate());
				String changedFormat = newFormat.format(originDate);
				project.setCreatedDate(changedFormat);
				
				Date originDate2 = originFormat.parse(project.getProjectStart());
				String changedFormat2 = newFormat.format(originDate2);
				project.setProjectStart(changedFormat2);
				
				Date originDate3 = originFormat.parse(project.getProjectEnd());
				String changedFormat3 = newFormat.format(originDate3);
				project.setProjectEnd(changedFormat3);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	}
}
