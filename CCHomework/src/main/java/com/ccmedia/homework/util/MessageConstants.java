package com.ccmedia.homework.util;

public class MessageConstants {
	
	public static String LoginFail = "로그인에 실패하였습니다. id,pw를 확인하세요";
	public static String ExistAccount = "해당 id가 이미 존재합니다.";
	
	public static String getMessage(String code) {
		String message = "";
		
		switch (code) {
		case "0000":
			message = "성공";
			break;
		case "1001":
			message = "게시판 조회 실패";
			break;
		case "1002":
			message = "상세조회 실패";
			break;
		case "1003":
			message = "등록 실패";
			break;
		case "1004":
			message = "수정 실패";
		case "1005":
			message = "삭제 실패";
		case "1006":
			message = "검색 실패";
		case "1007":
			message = "댓글 조회 실패";
		case "1008":
			message = "댓글 등록 실패";
		default:
			message = "there is not defined error";
			break;
		}
		return message;
	}
}
