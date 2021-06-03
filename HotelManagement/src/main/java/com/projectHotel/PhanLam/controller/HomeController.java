package com.projectHotel.PhanLam.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projectHotel.PhanLam.entity.TypeRoom;
import com.projectHotel.PhanLam.repository.ITypeRoom;

@Controller
@RequestMapping
public class HomeController {
	
	@Autowired
	private ITypeRoom typeroom;
	
	@GetMapping(value = {"/room","/"})
	public String getListRoom(Model model) {
		List<TypeRoom> listtypeRoom = typeroom.findAll();
		model.addAttribute("typeroom",listtypeRoom);	
	    return "index";
	}
	
}
