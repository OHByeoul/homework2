package com.ccmedia.homework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ccmedia.homework.model.BoardDTO;

public class Util {
	private static SimpleDateFormat originFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
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
	
	public static void setDateFormat(BoardDTO board){
		try {
				Date originDate = originFormat.parse(board.getYMD());
				String changedFormat = newFormat.format(originDate);
				board.setYMD(changedFormat);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	}
}