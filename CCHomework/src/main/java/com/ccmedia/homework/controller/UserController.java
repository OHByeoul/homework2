package com.ccmedia.homework.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ccmedia.homework.model.UserDTO;
import com.ccmedia.homework.service.UserServiceImpl;
import com.ccmedia.homework.util.MessageConstants;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class UserController {
	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping({"/","login"})
	public String initLogin(Model model) {
		return "login";
	}
	
	@PostMapping("/checkLogin")
	public String checkLogin(@ModelAttribute UserDTO user,HttpSession session, Model model) {
		UserDTO result = userService.checkLogin(user);
		if(result != null) {
			session.setAttribute("id", user.getUserId());
			return "redirect:/board";			
		} else {
			model.addAttribute("fail",MessageConstants.LoginFail);
			return "/login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session ) {
	    session.invalidate();
	    return "redirect:/login";
	}
	
	@GetMapping("/signUp")
	public String initSignUpPage(Model model) {
	    return "/signUp";
	} 
	
	@PostMapping("/registUser")
	public String signUp(@ModelAttribute UserDTO user, Model model) {
		int result = userService.signUp(user);
		if(result != 1) {
			model.addAttribute("fail",MessageConstants.ExistAccount);
		}
		return "login";
	}
}
