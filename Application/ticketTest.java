package project.xyz;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ticketTest {
	Seat testSeat = new Seat("a0", "Test", 50);

	Movie testMovie = new Movie("TestTitle","Test","English",java.time.LocalDate.now(),"This is a test movie");

	Ticket tick1 = new Ticket(12345 ,testMovie , testSeat);
	
	@Test
	void getTicket() {
		assertEquals(tick1.getTicketId(),103);
	}
	
	@Test
	void getUserId() {
		assertEquals(tick1.getUserId(),12345);
	}
	
	@Test
	void getMovie() {
		assertEquals(tick1.getMovie(), testMovie);
	}
	
	@Test
	void getSeat() {
		assertEquals(tick1.getSeat(), testSeat);
	}
	
	@Test
	void isCancelled() {
		assertFalse(tick1.isCancelled());
		
		tick1.cancelled();
		
		assertTrue(tick1.isCancelled());
	}
	
		
		
		
	
	
	@Test
	void TesttoString() {
		assertEquals(tick1.toString(),"ID: "+  tick1.getTicketId()+"\tUser: "+tick1.getUserId()+ "\tMovie: "+testMovie.getTitle()+"\nSeat: "+ testSeat.getSeatId()+"\t"+testSeat.getSeatShow());
		
	}
		
}
