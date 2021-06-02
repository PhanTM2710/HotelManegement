package com.projectHotel.PhanLam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String showHome() {
		return "index";
	}
	
	@GetMapping(value = "/hello")
	public String sayHello() {
	    return "search";
	}
}
