package com.projectHotel.PhanLam.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectHotel.PhanLam.entity.Room;
import com.projectHotel.PhanLam.entity.Service;
import com.projectHotel.PhanLam.entity.TypeRoom;
import com.projectHotel.PhanLam.repository.IBooking;
import com.projectHotel.PhanLam.repository.IRoom;
import com.projectHotel.PhanLam.repository.IService;
import com.projectHotel.PhanLam.repository.ITypeRoom;

@Controller
@RequestMapping
public class HomeController {
	@Autowired
	private IBooking booking;
	
	@Autowired
	private IRoom room;
	
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
	
	@GetMapping(value = "/showroom")
	public String showRoom(@RequestParam String startDateStr, @RequestParam String endDateStr, Model model) {
				
		if (startDateStr.isEmpty() || endDateStr.isEmpty()) {
		    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		    startDateStr = sdf.format(new Date());
		    endDateStr = startDateStr;
		}
		
	    System.out.println("startDate = " + startDateStr);
	    System.out.println("endDate = " + endDateStr);
		List<TypeRoom> listTypeRoom = typeroom.findAll();		
		List<Room> listRoom =room.filterRoomAvailable( startDateStr, endDateStr);
		model.addAttribute("listRoomall", listRoom);
		model.addAttribute("listTypeRoom", listTypeRoom);
		
		return "showRoom";
	}
}
