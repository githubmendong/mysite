package com.poscodx.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.annotation.WebServlet;

@WebServlet("/main")
@Controller
public class MainController {
	
	@RequestMapping("/")
	public String index() {
		return "main/index";
	}
	
}
