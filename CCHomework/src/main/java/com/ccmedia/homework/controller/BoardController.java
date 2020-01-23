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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccmedia.homework.model.BoardDTO;
import com.ccmedia.homework.model.NoticeDTO;
import com.ccmedia.homework.model.PagingDTO;
import com.ccmedia.homework.model.ResponseContainer;
import com.ccmedia.homework.service.BoardServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/board", method = RequestMethod.GET)
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardServiceImpl boardService;
	
	@Autowired
	PagingDTO pagingDTO;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@GetMapping({"/",""})
	public String initBoardList(Model model) {
		if(pagingDTO.getCurPageNum() > 0) {
			model.addAttribute("curPageNum", pagingDTO.getCurPageNum());
		}
		return "board";
	}

	@PostMapping(value = "/getBoardList", produces = "application/json; charset=utf8")
	@ResponseBody
	public String getBoardList(@RequestBody Map<String, String> params, Model model) {
		ResponseContainer<List> response = new ResponseContainer<List>();
		try {
			String curPageNum = params.get("curPageNum");

			String responseJson = boardService.getBoardList(params, response);

			model.addAttribute("curPageNum", curPageNum);
			return responseJson;
		} catch (Exception e) {
			logger.error("BoardController /getBoardList " + e.getMessage());
		}
		return response.getErrorMessage();
	}
	
	@GetMapping(value = "/createContent")
	public String initCreateContent(Model model) {
		return "boardCreate";
	}
	
	@PostMapping(value = "/createContent", produces = "application/json; charset=utf8")
	@ResponseBody
	public String createContent(@ModelAttribute BoardDTO boardDTO, Model model) {
		ResponseContainer<BoardDTO> response = new ResponseContainer<BoardDTO>();
		
		try {
			String responseJson = boardService.createContent(boardDTO,response);
			return responseJson;
		} catch (Exception e) {
			logger.error("BoardController /createContent " + e.getMessage());
		}
		return response.getErrorMessage();
	}
	
	
	
	@GetMapping(value = "/updateContent/{boardId}")
	public String initUpdateContent(@PathVariable String boardId, Model model) {
		model.addAttribute("boardId", boardId);
		return "boardUpdate";
	}
	
	@PostMapping(value = "/updateContent", produces = "application/json; charset=utf8")
	@ResponseBody
	public String modifyContent(@ModelAttribute BoardDTO boardDTO, Model model) {
		ResponseContainer<BoardDTO> response = new ResponseContainer<BoardDTO>();
		
		try {
			String responseJson = boardService.updateContent(boardDTO,response);
			return responseJson;
		} catch (Exception e) {
			logger.error("BoardController /updateContent " + e.getMessage());
		}
		return response.getErrorMessage();
	}
	
	@PostMapping(value = "/deleteContent", produces = "application/json; charset=utf8")
	@ResponseBody
	public String deleteContent(@RequestBody Map<String, String> params, Model model) {
		ResponseContainer<String> response = new ResponseContainer<String>();
		try {
			String responseJson = boardService.deleteContent(params, response);
			return responseJson;
		} catch (Exception e) {
			logger.error("BoardController /deleteContent " + e.getMessage());
		}
		return response.getErrorMessage();
	}

	@GetMapping(value = "/getDetailContent/{boardId}", produces = "application/json; charset=utf8")
	public String initDetailContent(@PathVariable String boardId, Model model) {
		model.addAttribute("boardId",boardId);
		return "boardDetail";
	}
	
	@PostMapping(value = "/getDetailContent", produces = "application/json; charset=utf8")
	@ResponseBody
	public String getDetailContent(@RequestBody Map<String, String> params, Model model) {
		ResponseContainer<BoardDTO> response = new ResponseContainer<BoardDTO>();
		try {
			String responseJson = boardService.getDetailContent(params,response);
			return responseJson;
		} catch (Exception e) {
			logger.error("BoardController /getDetailContent " + e.getMessage());
		}
		return response.getErrorMessage();
	}
	
	@GetMapping(value = "/searchBoardList", produces = "application/json; charset=utf8")
	public String searchBoardList(@RequestParam String searchName, Model model) {
		ResponseContainer<List> response = new ResponseContainer<List>();
		try {
			List<BoardDTO> boards = boardService.getSearchedList(searchName, response);
			model.addAttribute("boards",boards);
			return "boardSearch";
		} catch (Exception e) {
			logger.error("BoardController /searchBoardList " + e.getMessage());
		}
		return response.getErrorMessage();
	}
	
}

