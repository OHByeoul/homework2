package com.ccmedia.homework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ccmedia.homework.model.BoardDTO;
import com.ccmedia.homework.model.CommentDTO;
import com.ccmedia.homework.model.NoticeDTO;

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
}
