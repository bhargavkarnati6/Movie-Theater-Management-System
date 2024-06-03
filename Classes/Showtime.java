package project.xyz;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Showtime {
	private static int showtimeId = 1000;
	private LocalDate date;
	private LocalTime time;
	private ArrayList<Screen> screens;
	private ArrayList<String> movies;
	
	public Showtime(LocalDate date, LocalTime time) {
		this.date = date;
		this.time = time;
		screens = new ArrayList<>();
		movies = new ArrayList<>();
		showtimeId++;
	}
	
	public int getShowtimeId() {
		return showtimeId;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public LocalTime getTime() {
		return time;
	}
	
	public void addShow(Screen screen, String movie) {
		screen.setDate(date);
		screen.setTime(time);
		screens.add(screen);
		movies.add(movie);
	}
	
	public String toString() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM dd, yyyy");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
		String result = "Date: "+date.format(dateFormatter)+"\tTime: "+time.format(timeFormatter);
		return result;
	}
	
	public String printProgram() {
		String result = toString()+"\n";
		for (int i=0; i<screens.size(); i++) {
			result += screens.get(i).toString()+"\tMovie: "+movies.get(i)+"\n";
		}
		return result;
	}
}
