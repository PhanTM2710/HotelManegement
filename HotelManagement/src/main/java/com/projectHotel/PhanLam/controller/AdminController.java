package com.projectHotel.PhanLam.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectHotel.PhanLam.entity.Room;
import com.projectHotel.PhanLam.repository.ICreditCard;
import com.projectHotel.PhanLam.repository.IPayment;
import com.projectHotel.PhanLam.repository.IPromotion;
import com.projectHotel.PhanLam.repository.IRoom;
import com.projectHotel.PhanLam.repository.IService;
import com.projectHotel.PhanLam.repository.ITypeRoom;

@Controller
@RequestMapping
public class AdminController {
				
		@Autowired
		private IRoom room;
		
		@Autowired
		private ITypeRoom typeroom;
		
		@Autowired
		private IService service;

		@Autowired
		private IPayment payment;

		@Autowired
		private ICreditCard card;
		@Autowired
		private IPromotion promo;
					
		@GetMapping(value = "/login")
		public String getlogin() {
			return "login";
		}
		
		@GetMapping("/admin")
	    public String admin(Model model) {
			model.addAttribute("rooms",room.findAll());		
	        return "adminhome";
	    }
		
		@PostMapping(value = "/saveRoom")
		public String saveroom(Room r) {
			room.save(r);
			return "redirect:/admin";
		}
		
		@GetMapping(value = "/deleteRoom")
		public String deleteroom(int id) {
			Optional<Room> findRoom = room.findById(id);
			
			if(findRoom != null) {
				Room roomdelete = findRoom.get();
				roomdelete.setisDelete(true);
				room.save(roomdelete);
			}
			return "redirect:/admin";
		}
		
		@GetMapping("/findRoom")
		@ResponseBody
		public Optional<Room> findOne(int id){
			return room.findById(id);
		}

		@GetMapping("/logout")
		public String logout(HttpServletRequest request, HttpServletResponse response) {
		    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    if (auth != null) {
		        new SecurityContextLogoutHandler().logout(request, response, auth);
		    }
		    return "redirect:/login";
		}
		
		
		@GetMapping("/addroom")
	    public String addroom(Model model) {
	        model.addAttribute("addroom", new Room());
	        return "addRoom";
	    }
		
		@GetMapping("/editRoom")
	    public String updateRoom(@RequestParam int id, Model model) {
			Optional<Room> r=room.findById(id);
			Room findRoom =r.get();
	        model.addAttribute("room", findRoom);
	        model.addAttribute("typeroom", typeroom.findAll());
	        return "updateroom";
	    }
		
		@PostMapping("/updateroom")
	    public String doUpdateRoom(@ModelAttribute("room") Room rooms) {
			room.save(rooms);
	        return "redirect:/admin";
	    }
}
