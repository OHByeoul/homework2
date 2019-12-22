package com.ccmedia.homework.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccmedia.homework.model.Board;
import com.ccmedia.homework.service.BoardService;
import com.google.gson.Gson;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/board", method = RequestMethod.GET)
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;

	private Gson gson = new Gson();

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@GetMapping("/")
	public String initBoardList(Model model) {
		try {
			List<Board> boards = boardService.getBoardList();

		} catch (Exception e) {
				//TODO
		}
		return "board";

	}

	@PostMapping(value = "/getBoardList", produces = "application/json; charset=utf8")
	@ResponseBody
	public String getBoardList(Locale locale, Model model) {
		List<Board> boards = boardService.getBoardList();
		return gson.toJson(boards);
	}

}
