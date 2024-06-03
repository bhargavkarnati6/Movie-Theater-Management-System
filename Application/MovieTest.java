package project.xyz;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovieTest {
	Movie testMovie = new Movie("TestTitle","Test","English",java.time.LocalDate.now(),"This is a test movie");
	@Test
	void testgetTitle() {
		assertEquals(testMovie.getTitle(),"TestTitle");

		}

	@Test
	void testgetGenere(){
		assertEquals(testMovie.getGenre(),"Test");

	}
	
	
	@Test
	void testgetLanguage(){
		assertEquals(testMovie.getLanguage(),"English");
	}
	
	@Test
	void testgetDescription(){
		assertEquals(testMovie.getDescription(),"This is a test movie");
	}
	
	@Test
	void testgetReleased(){
		assertEquals(testMovie.getReleased(),java.time.LocalDate.now());
	}
	
	@Test
	void testToString(){
		assertEquals(testMovie.toString(),testMovie.getTitle()+"\tGenre: "+testMovie.getGenre()+"\tLanguage: "+testMovie.getLanguage()+"\tRelease Date: "+DatePrint.getDateString(testMovie.getReleased()));
	}
	
	@Test
	void testDisplayInfo(){
		assertEquals(testMovie.displayInfo(),"Title: " +testMovie.getTitle()+ "\nGenre: " + testMovie.getGenre()+"\nLanguage: "+ testMovie.getLanguage()+ "\nDescription:"+ testMovie.getDescription());
	
	}
	
	@Test
	void testPrintallInfo(){
		assertEquals(testMovie.printAllInfo(),testMovie.toString() +"\nDescription: "+testMovie.getDescription());
	
	}
	
	
	
}
