package com.projectHotel.PhanLam.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectHotel.PhanLam.entity.Booking;
import com.projectHotel.PhanLam.entity.BookingDetail;
import com.projectHotel.PhanLam.entity.BookingSession;
import com.projectHotel.PhanLam.entity.CreditCard;
import com.projectHotel.PhanLam.entity.Payment;
import com.projectHotel.PhanLam.entity.Promotion;
import com.projectHotel.PhanLam.entity.Room;
import com.projectHotel.PhanLam.entity.Service;
import com.projectHotel.PhanLam.entity.ServiceDetail;
import com.projectHotel.PhanLam.entity.TypeRoom;
import com.projectHotel.PhanLam.repository.IBooking;
import com.projectHotel.PhanLam.repository.IBookingDetail;
import com.projectHotel.PhanLam.repository.IBookingPerson;
import com.projectHotel.PhanLam.repository.ICreditCard;
import com.projectHotel.PhanLam.repository.IPayment;
import com.projectHotel.PhanLam.repository.IPromotion;
import com.projectHotel.PhanLam.repository.IRoom;
import com.projectHotel.PhanLam.repository.IService;
import com.projectHotel.PhanLam.repository.IServiceDetail;
import com.projectHotel.PhanLam.repository.ITypeRoom;


@Controller
@RequestMapping
public class HomeController {
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private BookingSession bookingsession;
	
	@Autowired
	private IBooking booking;
	
	@Autowired
	private IRoom room;
	
	@Autowired
	private ITypeRoom typeroom;
	
	@Autowired
	private IService service;
	
	@Autowired
	private IBookingPerson person;
	@Autowired
	private IPayment payment;
	@Autowired
	private IBookingDetail bookingdetail;
	@Autowired
	private IServiceDetail servicedetai;
	@Autowired
	private ICreditCard card;
	@Autowired
	private IPromotion promo;
		
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
	public String showRoom(@RequestParam String startDateStr, @RequestParam String endDateStr, Model model) throws ParseException {
				
		if (startDateStr.isEmpty() || endDateStr.isEmpty()) {
		    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		    startDateStr = sdf.format(new Date());
		    endDateStr = startDateStr;
		}
			
		List<Room> listRoom =room.filterRoomAvailable( startDateStr, endDateStr);
		List<BookingDetail> bookingDetails = bookingsession.getBookingDetail();
		bookingsession.setStartDate(startDateStr);
		bookingsession.setEndDate(endDateStr);
		long total = bookingsession.sumPrice();
		bookingsession.setAmount(total);
						
		model.addAttribute("bookingdetail", bookingDetails);
		model.addAttribute("listRoomall", listRoom);
		model.addAttribute("startDateStr", startDateStr);
		model.addAttribute("endDateStr", endDateStr);
		model.addAttribute("total", total);
		return "showRoom";
	}
	
	@GetMapping(value = "/choseService")
	public String ListService(@RequestParam String startDateStr,@RequestParam String endDateStr,Model model ) {
		
		bookingsession.setStartDate(startDateStr);
		bookingsession.setEndDate(endDateStr);
			
		List<Service> listService = service.findAll();
		List<ServiceDetail> listServiceDetail=bookingsession.getServiceDetails();
		long total = bookingsession.sumServicePrice();
		model.addAttribute("total",total);
		model.addAttribute("listServiceDetail", listServiceDetail);
		model.addAttribute("listservice",listService);					
		return "choseservice";
	}
	
	@GetMapping(value = "/addinfor")
	public String addinfor( Model model) throws ParseException {	
		long amountRS = bookingsession.totalRoomandService();	
		List<BookingDetail> bookingDetail = bookingsession.getBookingDetail();
		List<ServiceDetail> serviceDetails = bookingsession.getServiceDetails();		
		model.addAttribute("listservice",serviceDetails );
		model.addAttribute("amountRS", amountRS);
		model.addAttribute("amount", bookingsession.getAmount());
		model.addAttribute("startdate", bookingsession.getStartDate());
		model.addAttribute("enddate", bookingsession.getEndDate());
		model.addAttribute("bookingDetail", bookingDetail);
		return "infor";
	}
	
	@Transactional(value = TxType.REQUIRED)
	@GetMapping(value = "/summary")
	public String summary() {
		String id=bookingsession.randomId();
		bookingsession.setId(id);
//Send ID to mail customer		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("hotelluxury27@gmail.com");
		mailMessage.setTo(bookingsession.getPerson().getEmail());
		
		String mailSubject = "LUXURY HOTEL SEND CODE BOOKING";
		String mailText ="Your booking code:  "+ bookingsession.getId() +"\n"+"Do not give this booking code to others. Please!!!";
		
		mailMessage.setSubject(mailSubject);
		mailMessage.setText(mailText);
		
		mailSender.send(mailMessage);	
//Save database booking		
		person.save(bookingsession.getPerson());
		Booking bookinga = new Booking();
		bookinga.setId(bookingsession.getId());
		bookinga.setStartDate(bookingsession.getStartDate());
		bookinga.setEndDate(bookingsession.getEndDate());
		bookinga.setAmount(bookingsession.getAmount());
		bookinga.setPromotion(bookingsession.getPromotion());
		bookinga.setPerson(bookingsession.getPerson());	
		bookinga.setisDelete(false);
		booking.save(bookinga);	
		
		
		if(bookingsession.getPromotion() != null) {
			Optional<Promotion> promotion =promo.findById(bookingsession.getPromotion().getId());
			Promotion promotions = promotion.get();
			int promoTime = promotions.getPromotionTime();
			promoTime -= 1;
			promotions.setPromotionTime(promoTime);
			promo.save(promotions);
		}
//save payment		
		Payment payments = new Payment(bookingsession.getPayment().getDate(), bookingsession.getAmount(),bookingsession.getPayment().getDesciption(), bookingsession.getPayment().getCard(), bookinga,false);
		payment.save(payments);	
//save bookingdetail
		for (BookingDetail details : bookingsession.getBookingDetail()) {
			BookingDetail bookidetail = new BookingDetail(false,bookinga, details.getRoom()) ;
			bookingdetail.save(bookidetail);
		}
//save serviceDetail		
		for (ServiceDetail details : bookingsession.getServiceDetails()) {
			ServiceDetail serdetail = new ServiceDetail(false,bookinga, details.getService()) ;
			servicedetai.save(serdetail);
		}
//Tru tien khach hanng		
		Optional<CreditCard> cardtru = card.findById(bookingsession.getPayment().getCard().getId());
		CreditCard cardtruxong = cardtru.get();
		long amounttru = cardtru.get().getAmount();
		amounttru -= bookingsession.getAmount();
		cardtruxong.setAmount(amounttru);
		card.save(cardtruxong);
// cong tien tai khaon khach san	
		Optional<CreditCard> cards = card.findById(1);		
		if(cards != null) {
			long amount = cards.get().getAmount() ;
			amount += bookingsession.getAmount();				
			CreditCard cardupdate = cards.get();
			cardupdate.setAmount(amount);
			card.save(cardupdate);
		}
//remove session		
		bookingsession.removeall();
		
		 return "redirect:/searchbooking";
	}
	
	@GetMapping(value = "/searchbooking")
	public String checkbooking() {				
		return "searchBooking";
	}
	
	@PostMapping(value = "/showbooking")
	public String showbooking(String id,Model model) {
		Optional<Booking> codebooking = booking.findById(id);
		Booking bookings =codebooking.get();		
		model.addAttribute("booking", bookings);
		return "showbooking";		
	}
		
	@Transactional(value = TxType.REQUIRED)
	@PostMapping(value = "/cancelbooking")
	public String cancelbooking(String id ,Model model) {
		Optional<Booking> bookings = booking.findById(id);
		Booking bookinga = bookings.get();
		bookinga.setisDelete(true);
		booking.save(bookinga);		
														
		CreditCard cardKH = bookinga.getPayment().getCard();
		long amountcong = cardKH.getAmount();
		amountcong += bookinga.getAmount()*(80/100);
		cardKH.setAmount(amountcong);
		card.save(cardKH);

		Optional<CreditCard> cards = card.findById(1);		
		if(cards != null) {
			long amount = cards.get().getAmount() ;
			amount -= bookinga.getAmount()*(20/100);				
			CreditCard cardupdate = cards.get();
			cardupdate.setAmount(amount);
			card.save(cardupdate);
		}	
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("hotelluxury27@gmail.com");
		mailMessage.setTo(bookinga.getPerson().getEmail());
		
		String mailSubject = "LUXURY HOTEL REFUNDS BOOKING";
		String mailText ="Luxury Hotels refund customers, check your account again, please!. Have a good time, see you next time.\n"   + "Booking amount:   " +  bookinga.getAmount()+" VND\n" 
							+ "Money back:       " + (bookinga.getAmount()*80/100) +" VND\n"
							+ "CreditCard money: " + amountcong + " VND";
		
		mailMessage.setSubject(mailSubject);
		mailMessage.setText(mailText);		
		mailSender.send(mailMessage);
		
		return "redirect:/room";
	}
}
