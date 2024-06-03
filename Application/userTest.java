package project.xyz;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class userTest {
	
	Seat testSeat = new Seat("a0", "Test", 50);
	User testUser = new User("test@gmail.com", "123456");
	Movie testMovie = new Movie("TestTitle","Test","English",java.time.LocalDate.now(),"This is a test movie");
	Ticket testTicket = new Ticket(testUser.getUserId(),testMovie,testSeat);
	int idCounter = 13;
	@Test
	void testGetUserID() {
		assertEquals(testUser.getUserId(),idCounter);
		idCounter++;
	}
	
	@Test
	void testGetEmail() {
		assertEquals(testUser.getEmail(),"test@gmail.com");
	}
	
	@Test
	void testPassword() {
		assertTrue(testUser.checkPassword("123456"));
		testUser.setPassword("abcdef");
		assertTrue(testUser.checkPassword("abcdef"));
	}

	@Test
	void TestTicket() {
		assertFalse(testUser.getTickets().size()!=0);
		testUser.addTicket(testTicket);
		
		assertTrue(testUser.getTickets().size()==1);		
		assertEquals(testUser.getTickets().get(0), testTicket);
		
		testUser.delTicket(testTicket);
		assertFalse(testUser.getTickets().size()!=0);
	}
	@Test
	void testToString() {
		assertEquals(testUser.toString(), "ID: "+testUser.getUserId()+"\tEmail: test@gmail.com");
	}
	
}
