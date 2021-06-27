package com.projectHotel.PhanLam.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

import com.projectHotel.PhanLam.entity.Booking;
import com.projectHotel.PhanLam.entity.BookingPerson;
import com.projectHotel.PhanLam.entity.BookingSession;
import com.projectHotel.PhanLam.entity.CreditCard;
import com.projectHotel.PhanLam.entity.Payment;
import com.projectHotel.PhanLam.entity.Promotion;
import com.projectHotel.PhanLam.entity.Room;
import com.projectHotel.PhanLam.entity.Service;
import com.projectHotel.PhanLam.repository.IBooking;
import com.projectHotel.PhanLam.repository.ICreditCard;
import com.projectHotel.PhanLam.repository.IPromotion;
import com.projectHotel.PhanLam.repository.IRoom;
import com.projectHotel.PhanLam.repository.IService;

@RestController
@SessionScope
@PreAuthorize("permitAll()")
public class BookingController {
	
	@Autowired
	private BookingSession bookingSession;	
	
	@Autowired
	private IBooking booking;

	@Autowired
	private IRoom room;
	
	@Autowired
	private IService service;
	
	@Autowired
	private IPromotion promo;
	
	@Autowired
	private ICreditCard creditCard;
		
	
	@PreAuthorize("permitAll()")
	@PostMapping(value = "/addpayment")
	public ResponseEntity<?> addpayment(String name,String phone, String address, String email, String birthday,int id,String descr,String idpro ) throws ParseException{
		Optional<CreditCard> card = creditCard.findById(id);			
		
		long price = bookingSession.getAmount();
		if (card != null) {
			if(card.get().getAmount() >= price) {
				BookingPerson person = new BookingPerson();
				person.setName(name);
				person.setPhone(phone);
				person.setAddress(address);
				person.setEmail(email);
				person.setBirthday(birthday);
				person.setisDelete(false);
				bookingSession.setPerson(person);
				
				if (!"".equals(idpro)) {
					int idpromo = Integer.parseInt(idpro);
					Optional<Promotion> promotion = promo.findById(idpromo);
					if((!promotion.isEmpty()) && (promotion.get().getPromotionTime() > 0)){
						bookingSession.setPromotion(promotion.get());
						bookingSession.setAmount(bookingSession.priceend());
					}
				}
				
				bookingSession.randomId();
				CreditCard creditCard = card.get();
				String description =  descr;
				Date current = new Date();
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  
				String strDate = dateFormat.format(current);

				Payment payment = new Payment();
				payment.setDate(strDate);
				payment.setAmount(price);
				payment.setDesciption(description);
				payment.setCard(creditCard);
				payment.setisDelete(false);
				bookingSession.setPayment(payment);
				return ResponseEntity.ok(payment);				
			}else {			
				return ResponseEntity.ok("");
			}
		}else {
			return ResponseEntity.ok("");
		}
	}	
	
	@PreAuthorize("permitAll()")
	@PostMapping(value = "/addroom")
	public ResponseEntity<?> addRoom(Integer id) throws ParseException{
		Optional<Room> rooms= room.findById(id);
		Room room = rooms.get();
		boolean successFlg  = bookingSession.addBookingDetail(room);
		
		if (successFlg) {
			return ResponseEntity.ok(room);
		} else {
			return ResponseEntity.ok("");
		}
	}
	
	@PreAuthorize("permitAll()")
	@PostMapping(value = "/delete")
	public ResponseEntity<?> removeRoom(Integer id){
		Optional<Room> rooms= room.findById(id);
		Room room = rooms.get();
		boolean successFlg  = bookingSession.delete(room);
		
		if (successFlg) {
			return ResponseEntity.ok(room);
		} else {
			return ResponseEntity.ok("");
		}
	}	
	
	@PreAuthorize("permitAll()")
	@PostMapping(value = "/addservice")
	public ResponseEntity<?> addService(Integer id){
		Optional<Service> services = service.findById(id);
		Service service = services.get();
		boolean successFlg  = bookingSession.addServiceDetail(service);
		
		if (successFlg) {
			return ResponseEntity.ok(service);
		} else {
			return ResponseEntity.ok("");
		}
	}
	
	@PreAuthorize("permitAll()")
	@PostMapping(value = "/deleteservice")
	public ResponseEntity<?> removeService(Integer id){
		Optional<Service> services = service.findById(id);
		Service service = services.get();
		boolean successFlg  = bookingSession.deleteService(service);
		
		if (successFlg) {
			return ResponseEntity.ok(service);
		} else {
			return ResponseEntity.ok("");
		}
	}	
	
	@PreAuthorize("permitAll()")
	@PostMapping(value = "/checkPromo")
	public ResponseEntity<?> checkpromo(String code) throws ParseException{
		Promotion promotion = promo.findByCode(code);
		
		if (promotion != null) {
			Date current = new Date();
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  
			Date expirationDate = dateFormat.parse(promotion.getExpirationDate());
			Date effectiveDate = dateFormat.parse(promotion.getEffectiveDate()); 
			if((current.getTime() <= expirationDate.getTime()) 
					&& (current.getTime() >= effectiveDate.getTime())
					&& (promotion.getPromotionTime()>0)) {
				return ResponseEntity.ok(promotion);
			}else {
				return ResponseEntity.ok("");
			}						
		} else {
			return ResponseEntity.ok("");
		}
	}	
			
	@PreAuthorize("permitAll()")
	@PostMapping(value = "/checkcard")
	public ResponseEntity<?> checkCard(String cardNumber , long money) throws ParseException{
		CreditCard card = creditCard.findByCardNumber(cardNumber);
		
		if (card != null) {	
			if (money <= card.getAmount()) {
				return ResponseEntity.ok(card);
			}
			return ResponseEntity.ok("Amount not enough");
		} else {
			return ResponseEntity.ok("");
		}
	}
	
	@PreAuthorize("permitAll()")
	@PostMapping(value = "/checkCodeBooking")
	public ResponseEntity<?>  checCodeBooking(String id) throws ParseException{
		Optional<Booking> bookings = booking.findById(id);
		if (!bookings.isEmpty()) {
			if(bookings.get().isisDelete()==false) {
				return ResponseEntity.ok(bookings);
			} else {
				return ResponseEntity.ok("");
			}
		} else {
			return ResponseEntity.ok("");
		}
	}
	
	@PreAuthorize("permitAll()")
	@PostMapping(value = "/cancel")
	public ResponseEntity<?>  cancel(String id) throws ParseException{
		Optional<Booking> bookings = booking.findById(id);
		if (!bookings.isEmpty()) {
			Date current = new Date();
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  
			Date startdate = dateFormat.parse(bookings.get().getStartDate());
			if(bookings.get().isisDelete()==false && (current.getTime() < startdate.getTime())) {
				return ResponseEntity.ok(bookings);
			} else {
				return ResponseEntity.ok("");
			}
		} else {
			return ResponseEntity.ok("");
		}
	}
}
