package project.xyz;

import java.io.Serializable;
import java.time.*;

public class Screen implements Serializable {
	private static final long serialVersionUID = 1L;
	private final int SCREEN_ID;
	private String screenType;
	private double basePrice;
	private Seat[] layout;
	private LocalDate date;
	private LocalTime time;
	
	public Screen (int screenId, String type, double price, Seat[] layout) {
		SCREEN_ID = screenId;
		this.screenType = type;
		this.basePrice = price;
		this.layout = deepCopyArray(layout);
	}
	
	public int getScreenId() {
		return SCREEN_ID;
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
	
	public Seat[] getSeats() {
		return layout;
	}
	
	public Seat[] deepCopyArray(Seat[] layout) {
		Seat[] output = new Seat[layout.length];
		for (int i=0; i<layout.length; i++) {
			output[i] = new Seat(layout[i].getSeatId(), layout[i].getSeatType(), layout[i].getAdditionalPrice());
			output[i].setScreen(SCREEN_ID, screenType, basePrice);
		}
		return output;
	}

	public String toString() {
		String result = "Screen: "+SCREEN_ID+"\tType: "+screenType+"\tCapacity: "+layout.length;
		return result;
	}
	
	public String displaySeats() {
		String result = "========Screen========\n\n";
		for (int i=0; i<layout.length; i++) {
			if (layout[i].getSeatId().charAt(0)=='F') {
				if (layout[i].getSeatId().equals("F1")) {
					result += "   "+layout[i-1].getSeatId().charAt(0)+": $"+layout[i-1].getTotalPrice()+"\n";
				}
				if (layout[i].getSeatId().charAt(1)=='3') {
					result += " ";
				}
				if (layout[i].getHolder()==0) {
					result += " ["+layout[i].getSeatId()+"]";
				}
				else {
					result += " [XX]";
				}
				if (layout[i].getSeatId().equals("F4")) {
					result += "   F: $"+layout[i].getTotalPrice();
				}
			}
			else {
				if ((i>0)&&(layout[i].getSeatId().charAt(0)!=layout[i-1].getSeatId().charAt(0))) {
					result += "   "+layout[i-1].getSeatId().charAt(0)+": $"+layout[i-1].getTotalPrice()+"\n";
				}
				if (layout[i].getSeatId().charAt(1)=='4') {
					result += "   ";
				}
				if (layout[i].getHolder()==0) {
					result += " "+layout[i].getSeatId();
				}
				else {
					result += " XX";
				}
			}
		}
		return result;
	}
	
	public String printAllSeat() {
		String result = toString()+"\n";
		for (int i=0; i<layout.length; i++) {
			result += layout[i].getSeatInfo()+"\n";
		}
		return result;
	}
}
