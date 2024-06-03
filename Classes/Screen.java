package project.xyz;

import java.time.*;

public class Screen {
	private int screenId;
	private String screenType;
	private double basePrice;
	private Seat[] layout;
	private LocalDate date;
	private LocalTime time;
	
	public Screen (int screenId, String type, double price, Seat[] layout) {
		this.screenId = screenId;
		this.screenType = type;
		this.basePrice = price;
		this.layout = deepCopyArray(layout);
		date = null;
		time = null;
	}
	
	public int getScreenId() {
		return screenId;
	}
	
	public String getScreenType() {
		return screenType;
	}
	
	public double getBasePrice() {
		return basePrice;
	}

	public LocalDate getDate() {
		return date;
	}
	
	public LocalTime getTime() {
		return time;
	}
	
	protected void setDate(LocalDate date) {
		this.date = date;
	}
	
	protected void setTime(LocalTime time) {
		this.time = time;
	}
	
	public Seat[] deepCopyArray(Seat[] layout) {
		Seat[] output = new Seat[layout.length];
		for (int i=0; i<layout.length; i++) {
			output[i] = new Seat(layout[i].getSeatId(), layout[i].getSeatType(), layout[i].getAdditionalPrice());
			output[i].setScreen(screenId, screenType, basePrice);
			output[i].setDateTime(date, time);
		}
		return output;
	}

	public String printAllSeat() {
		String result = toString()+"\n";
		for (int i=0; i<layout.length; i++) {
			result += layout[i].toString()+"\n";
		}
		return result;
	}
	
	public String toString() {
		String result = "Screen: "+screenId+"\tType: "+screenType+"\tCapacity: "+layout.length;
		return result;
	}
}
