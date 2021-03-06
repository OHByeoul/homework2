package com.ccmedia.homework.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccmedia.homework.model.NoticeDTO;
import com.ccmedia.homework.model.ResponseContainer;
import com.ccmedia.homework.service.NoticeServiceImpl;

@Controller
@RequestMapping(value = "/notice", method = RequestMethod.GET)
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	NoticeServiceImpl noticeService;
	
	@GetMapping(value = "/createNoticeContent")
	public String initNoticeCreateContent(Model model) {
		return "notice/noticeCreate";
	}
	
	@PostMapping(value = "/getNoticeList", produces = "application/json; charset=utf8")
	@ResponseBody
	public String getBoardList(@RequestBody Map<String, String> params, Model model) {
		ResponseContainer<List> response = new ResponseContainer<List>();
		try {
			String curPageNum = params.get("curPageNum");

			String responseJson = noticeService.getNoticeList(params, response);

			model.addAttribute("curPageNum", curPageNum);
			return responseJson;
		} catch (Exception e) {
			logger.error("NoticeController /getNoticeList " + e.getMessage());
		}
		return response.getErrorMessage();
	}
	
	@PostMapping(value = "/createNoticeContent", produces = "application/json; charset=utf8")
	@ResponseBody
	public String createNoticeContent(@ModelAttribute NoticeDTO noticeDTO, Model model) {
		ResponseContainer<NoticeDTO> response = new ResponseContainer<NoticeDTO>();
		
		try {
			String responseJson = noticeService.createNoticeContent(noticeDTO,response);
			return responseJson;
		} catch (Exception e) {
			logger.error("NoticeController /createContent " + e.getMessage());
		}
		return response.getErrorMessage();
	}
	
	@GetMapping(value = "/getNoticeDetailContent/{noticeId}", produces = "application/json; charset=utf8")
	public String initNoticeDetailContent(@PathVariable String noticeId, Model model) {
		model.addAttribute("noticeId",noticeId);
		return "notice/noticeDetail";
	}
	
	@PostMapping(value = "/getNoticeDetailContent", produces = "application/json; charset=utf8")
	@ResponseBody
	public String getNoticeDetailContent(@RequestBody Map<String, String> params, Model model) {
		ResponseContainer<NoticeDTO> response = new ResponseContainer<NoticeDTO>();
		try {
			String responseJson = noticeService.getNoticeDetailContent(params,response);
			return responseJson;
		} catch (Exception e) {
			logger.error("NoticeController /getNoticeDetailContent " + e.getMessage());
		}
		return response.getErrorMessage();
	}
	
	@GetMapping(value = "/updateNoticeContent/{noticeId}")
	public String initUpdateContent(@PathVariable String noticeId, Model model) {
		model.addAttribute("noticeId", noticeId);
		return "notice/noticeUpdate";
	}
	
	@PostMapping(value = "/updateNoticeContent", produces = "application/json; charset=utf8")
	@ResponseBody
	public String modifyContent(@ModelAttribute NoticeDTO noticeDTO, Model model) {
		ResponseContainer<NoticeDTO> response = new ResponseContainer<NoticeDTO>();
		
		try {
			String responseJson = noticeService.updateNoticeContent(noticeDTO,response);
			return responseJson;
		} catch (Exception e) {
			logger.error("NoticeController /updateContent " + e.getMessage());
		}
		return response.getErrorMessage();
	}
	
	@PostMapping(value = "/deleteContent", produces = "application/json; charset=utf8")
	@ResponseBody
	public String deleteContent(@RequestBody Map<String, String> params, Model model) {
		ResponseContainer<String> response = new ResponseContainer<String>();
		try {
			String responseJson = noticeService.deleteContent(params, response);
			return responseJson;
		} catch (Exception e) {
			logger.error("NoticeController /deleteContent " + e.getMessage());
		}
		return response.getErrorMessage();
	}
}
