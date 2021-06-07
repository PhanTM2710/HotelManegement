package com.projectHotel.PhanLam.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectHotel.PhanLam.entity.Service;
import com.projectHotel.PhanLam.entity.TypeRoom;
import com.projectHotel.PhanLam.repository.IService;
import com.projectHotel.PhanLam.repository.ITypeRoom;

@Controller
@RequestMapping
public class HomeController {
	
	@Autowired
	private ITypeRoom typeroom;
	
	@Autowired
	private IService service;
		
	@GetMapping(value = {"/room","/"})
	public String getListRoom(Model model) {
		List<TypeRoom> listtypeRoom = typeroom.findAll();
		model.addAttribute("typeroom",listtypeRoom);	
	    return "index";
	}
	
	@GetMapping(value = "/service")
	public String getListService(Model model) {
		List<Service> listService = service.findAll();
		model.addAttribute("listservice",listService);			
		return "service";
	}
	
	@GetMapping("/showmore")
	@ResponseBody
	public Optional<Service> findOne(Integer id){
		return service.findById(id);
	}
	
	@GetMapping(value = "test")
	public String test() {
		return "showRoom";
	}
}
