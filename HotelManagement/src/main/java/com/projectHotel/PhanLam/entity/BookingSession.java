package com.projectHotel.PhanLam.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class BookingSession {

	private String id;	
	private String startDate;
	private String endDate;
	private long amount;
	
	private Payment payment;
	
	private BookingPerson person;
	
	private Promotion promotion;
	
	private List<ServiceDetail> serviceDetails = new ArrayList<ServiceDetail>();
	
	
	private List<BookingDetail> bookingDetail = new ArrayList<BookingDetail>();
	
	
	public long totalRoomandService() {

		long priceRoom =amount; 
		long priceService =0;		
		for (ServiceDetail serviceDetail : serviceDetails) {
			priceService += serviceDetail.getService().getPrice();
		}
		
		long amount = (priceRoom + priceService);
		this.setAmount(amount);		
		return amount;
	}
	
	public long priceend() {
		long discount = promotion.getDiscount();
		long amounts = amount - ((amount)*discount/100);			
		return amounts;
	}
	
	public ServiceDetail findServiceById(int id) {
		for (ServiceDetail line : serviceDetails) {
			if(line.getService().getId() == id) {
				return line;
			}
		}
		return null;
	}
	
	public boolean addServiceDetail(Service service) {
		ServiceDetail Detail =findServiceById(service.getId());
		
		if(Detail == null) {
			ServiceDetail detail = new ServiceDetail();
			detail.setService(service);
			serviceDetails.add(detail);
			return true;
		}
		
		return false;
	}
	
	public boolean deleteService(Service service) {
		ServiceDetail Detail =findServiceById(service.getId());
		if(Detail != null){
			serviceDetails.remove(Detail);
			return true;
		}
		return false;
	}
	
	public BookingDetail findLineById(int id) {
		for (BookingDetail line : bookingDetail) {
			if(line.getRoom().getId() == id) {
				return line;
			}
		}
		return null;
	}
	
	public List<BookingDetail> getBookingDetail() {
		return bookingDetail;
	}

	public void setBookingDetail(List<BookingDetail> bookingDetail) {
		this.bookingDetail = bookingDetail;
	}
		
	public boolean addBookingDetail(Room room) {
		BookingDetail Detail =findLineById(room.getId());
		
		if(Detail == null) {
			BookingDetail detail = new BookingDetail();
			detail.setRoom(room);
			bookingDetail.add(detail);
			return true;
		}
		
		return false;
	}
				
	public String randomId() {
		String uuid = UUID.randomUUID().toString();	
		String id = uuid.substring(0, uuid.indexOf("-")).toUpperCase();
		this.setId(id);
		return id;
	}
	
	public boolean delete(Room room) {
		BookingDetail Detail =findLineById(room.getId());
		if(Detail != null){
			bookingDetail.remove(Detail);
			return true;
		}
		return false;
	}
	
	public long sumPrice() throws ParseException {
		long total=0;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		if (startDate == null || "".equals(startDate)) {
			startDate = sdf.format(new Date());
		}
		
		if (endDate == null || "".equals(endDate)) {
			endDate = sdf.format(new Date());
		}
		
		Date start = new SimpleDateFormat("MM/dd/yyyy").parse(startDate);
		Date end = new SimpleDateFormat("MM/dd/yyyy").parse(endDate);
		for (BookingDetail bookingDetail2 : bookingDetail) {
			total += bookingDetail2.getRoom().getPrice();
		}
		
		long diffInMillies = end.getTime() - start.getTime();
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1;
	    
	    total = total * diff;
		amount =total;
		return total;
	}
	
	public long sumServicePrice() {
		long total=0;
		for (ServiceDetail serviceDetail : serviceDetails) {
			total += serviceDetail.getService().getPrice();
		}
		
		return total;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}


	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public BookingPerson getPerson() {
		return person;
	}

	public void setPerson(BookingPerson person) {
		this.person = person;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public List<ServiceDetail> getServiceDetails() {
		return serviceDetails;
	}

	public void setServiceDetails(List<ServiceDetail> serviceDetails) {
		this.serviceDetails = serviceDetails;
	}
	
	public void removeall() {
		serviceDetails.removeAll(serviceDetails);
		bookingDetail.removeAll(bookingDetail);
		
	}
}
