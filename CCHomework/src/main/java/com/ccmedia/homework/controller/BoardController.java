package com.ccmedia.homework.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccmedia.homework.model.BoardDTO;
import com.ccmedia.homework.model.PagingDTO;
import com.ccmedia.homework.service.BoardServiceImpl;
import com.ccmedia.homework.util.Constants;
import com.google.gson.Gson;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/board", method = RequestMethod.GET)
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardServiceImpl boardService;

	private Gson gson = new Gson();

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@GetMapping("/")
	public String initBoardList(Model model) {
		try {
			List<BoardDTO> boards = boardService.getBoardList();

		} catch (Exception e) {
				//TODO
		}
		return "board";

	}

	@PostMapping(value = "/getBoardList", produces = "application/json; charset=utf8")
	@ResponseBody
	public String getBoardList(@RequestBody Map<String,String> params, Model model) {
		PagingDTO pagingDTO = new PagingDTO();
		String curPageNum = params.get("curPageNum");
		String listSize = Constants.listSize;
		
		String totalCnt = boardService.getTotalCnt();
		pagingDTO.setTotalCnt(totalCnt);
		pagingDTO.setCurPageNum(curPageNum);
		pagingDTO.setListSize(listSize);
		List<BoardDTO> boards = boardService.getBoardList();
		
		List<Object> response = new ArrayList<Object>();
		response.add(pagingDTO);
		response.add(boards);
		model.addAttribute("curPageNum", curPageNum);
		return gson.toJson(response);
	}

}
