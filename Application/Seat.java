package project.xyz;

import java.io.Serializable;
import java.time.*;

public class Seat implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String SEAT_ID;
	private String seatType;
	private double additionalPrice;
	private int holder;
	private int screenId;
	private String screenType;
	private double basePrice;
	private LocalDate date;
	private LocalTime time;
	
	public Seat(String seatId, String seatType, double additionalPrice) {
		SEAT_ID = seatId;
		this.seatType = seatType;
		this.additionalPrice = additionalPrice;
		screenId = 0;
		screenType = null;
		basePrice = 0;
		date = null;
		time = null;
		holder = 0;
	}
	
	public String getSeatId() {
		return SEAT_ID;
	}
	
	public String getSeatType() {
		return seatType;
	}
	
	public double getAdditionalPrice() {
		return additionalPrice;
	}
	
	public double getTotalPrice() {
		return basePrice+additionalPrice;
	}

	public LocalDate getDate() {
		return date;
	}
	
	public LocalTime getTime() {
		return time;
	}
	
	public int getHolder() {
		return holder;
	}
	
	public int getScreenId() {
		return screenId;
	}
	
	public String getScreenType() {
		return screenType;
	}
	
	protected void setScreen(int screenId, String screenType, double basePrice) {
		this.screenId = screenId;
		this.screenType = screenType;
		this.basePrice = basePrice;
	}
	
	protected void setDateTime(LocalDate date, LocalTime time) {
		this.date = date;
		this.time = time;
	}
	
	public void setHolder(int customerId) {
		holder = customerId;
	}
	
	public String getSeatInfo() {
		String holderText = "-";
		if (holder!=0) {
			holderText = Integer.toString(holder);
		}
		return "Seat: "+SEAT_ID+"\tType: "+seatType+"\tHolder: "+holderText+"\tPrice: "+getTotalPrice();
	}
	
	public String displayInTicket() {
		String output = "\tDate: "+DatePrint.getDateString(date)+"\tTime: "+DatePrint.getTimeString(time)+
				"\n\tScreen: "+screenId+"\n\tSeat: "+SEAT_ID;
		return output;
	}
	
	public String getSeatShow() {
		return "Screen: "+screenId+"\tDate: "+DatePrint.getDateString(date)+"\tTime: "+DatePrint.getTimeString(time);
	}
	
	public String toString() {
		return getSeatInfo()+"\n"+getSeatShow();
	}
}
