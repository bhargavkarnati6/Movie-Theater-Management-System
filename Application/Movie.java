package project.xyz;

import java.io.Serializable;
import java.time.*;

public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String TITLE;
	private String genre;
	private String language;
	private String description;
	private LocalDate released;
	
	public Movie(String title, String genre, String language, LocalDate released, String description) {
		TITLE = title;
		this.genre = genre;
		this.language = language;
		this.description = description;
		this.released = released;
	}

	public String getTitle() {
		return TITLE;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public String getDescription() {
		return description;
	}
	
	public LocalDate getReleased() {
		return released;
	}
	
	public String toString() {
		return TITLE+"\tGener: "+genre+"\tLanguage: "+language+"\tRelease Date: "+DatePrint.getDateString(released);
	}
	
	public String displayInfo() {
		return "Title: "+TITLE+"\nGenre: "+genre+"\nLanguage: "+language+"\nDescription:"+description;
	}
	
	public String printAllInfo() {
		return toString()+"\nDescription: "+description;
	}
}
