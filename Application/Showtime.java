package project.xyz;

import java.util.*;
import java.io.Serializable;
import java.time.*;

public class Showtime implements Serializable {
	private static final long serialVersionUID = 1L;
	private static int showtimeId = 1001;
	private final int SHOWTIME_ID;
	private LocalDate date;
	private LocalTime time;
	private ArrayList<Screen> screens;
	private ArrayList<Movie> movies;
	
	public Showtime(LocalDate date, LocalTime time) {
		SHOWTIME_ID = showtimeId++;
		this.date = date;
		this.time = time;
		screens = new ArrayList<>();
		movies = new ArrayList<>();
	}
	
	public int getShowtimeId() {
		return SHOWTIME_ID;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public LocalTime getTime() {
		return time;
	}
	
	public ArrayList<Screen> getScreens() {
		return screens;
	}
	
	public ArrayList<Movie> getMovies() {
		return movies;
	}
	
	public void addShow(Screen screen, Movie movie) {
		Seat[] seats = screen.getSeats();
		for (int i=0; i<seats.length; i++) {
			seats[i].setDateTime(date, time);
		}
		screens.add(screen);
		movies.add(movie);
	}
	
	public String toString() {
		String result = "ID: "+SHOWTIME_ID+"\tDate: "+DatePrint.getDateString(date)+"\tTime: "+DatePrint.getTimeString(time);
		return result;
	}
	
	public String printProgram() {
		String result = toString()+"\n";
		for (int i=0; i<screens.size(); i++) {
			result += screens.get(i).toString()+"\tMovie: "+movies.get(i).getTitle()+"\n";
		}
		return result;
	}
}
