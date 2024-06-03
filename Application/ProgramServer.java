package project.xyz;

import java.io.*;
import java.util.*;
//import java.net.*;

public class ProgramServer {
	public static void main (String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		//Create user
		ArrayList<User> users = new ArrayList<>();
		User inUse = null;
		try {
			WriteOutput.createUserFile();
			users = ReadInput.userList();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		boolean isLogin = false;
		while (!isLogin) {
			System.out.print("Register(Y/N): ");
			String regs = scanner.next();
			if (regs.equals("Y")) {
				boolean repeatedEmail = true;
				String email = null;
				while (repeatedEmail) {
					repeatedEmail = false;
					System.out.print("Enter email: ");
					email = scanner.next();
					for (int i=0; i<users.size(); i++) {
						if (users.get(i).getEmail().equals(email)) {
							repeatedEmail = true;
							System.out.println("This email has been used. Please try again.");
							break;
						}
					}
				}
				System.out.print("Enter password: ");
				String password = scanner.next();
				User user = new User(email, password);
				try {
					WriteOutput.addUser(user);
					System.out.println("Account has been created.\n");
				}
				catch (IOException e) {
					System.out.println(e.getMessage());
				}
				catch (ClassNotFoundException e) {
					System.out.println(e.getMessage());
				}
			}
			//Login
			System.out.print("Email: ");
			String email = scanner.next();
			System.out.print("Password: ");
			String pass = scanner.next();
			try {
				users = ReadInput.userList();
			}
			catch (IOException e) {
				System.out.println(e.getMessage());
			}
			catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
			for (int i=0; i<users.size(); i++) {
				if (users.get(i).getEmail().equals(email)) {
					inUse = users.get(i);
					break;
				}
			}
			if (inUse.equals(null)) {
				System.out.println("Invalid email. Please try  again.");
			}
			else {
				if (inUse.checkPassword(pass)) {
					isLogin = true;
				}
				else {
					System.out.println("Incorrect password. Please try again.");
				}
			}
		}
		
		//Print all movie
		System.out.println("Movie List:\n");
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		try {
			movieList = ReadInput.availableMovie();
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		for (int i=0; i<movieList.size(); i++) {
			System.out.println(movieList.get(i).printAllInfo()+"\n");
		}
		
		//Select movie
		Movie selectedMovie = null;
		System.out.print("Select movie by input title: ");
		String title = scanner.next();
		
		//Print available showtime.
		ArrayList<Showtime> showtimeList = new ArrayList<>();
		try {
			showtimeList = ReadInput.availableShowtime();
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("The available showtimes are:\n");
		for (int i=0; i<showtimeList.size(); i++) {
			for (int j=0; j<showtimeList.get(i).getMovies().size(); j++) {
				if (showtimeList.get(i).getMovies().get(j).getTitle().equals(title)) {
					System.out.println(showtimeList.get(i).toString());
					System.out.println(showtimeList.get(i).getScreens().get(j).toString()+"\n");
					selectedMovie = showtimeList.get(i).getMovies().get(j);
				}
			}
		}
		
		//Select showtime
		System.out.print("Select showtime by input ID: ");
		int showtimeId = scanner.nextInt();
		
		//Print available seat
		Screen selectedScreen = null;
		for (int i=0; i<showtimeList.size(); i++) {
			if (showtimeList.get(i).getShowtimeId()==showtimeId) {
				Showtime showtime = showtimeList.get(i);
				for (int j=0; j<showtime.getMovies().size(); j++) {
					if (showtime.getMovies().get(j).getTitle().equals(title)) {
						selectedScreen = showtime.getScreens().get(j);
					}
				}
			}
		}
		System.out.println("The available seats are:");
		System.out.println(selectedScreen.displaySeats());

		//Select seat
		Seat selectedSeat = null;
		System.out.print("Select seat by input seat: ");
		String seatId = scanner.next();
		Seat[] seats = selectedScreen.getSeats();
		for (int i=0; i<seats.length; i++) {
			if (seats[i].getSeatId().equals(seatId)) {
				selectedSeat = seats[i];
				break;
			}
		}
		
		//Create ticket
		int userId = inUse.getUserId();
		Ticket newTicket = new Ticket(userId, selectedMovie, selectedSeat);
		selectedSeat.setHolder(userId);
		inUse.addTicket(newTicket);
		System.out.println("Book ticket complete.\nDetails: "+newTicket.toString());
		scanner.close();
		
		//Check booked seat in the screen
		System.out.println(selectedScreen.printAllSeat());
		
		
		//Server part. ref Lab6 CS1083 fall2023: Rick Wightman(author), December 2022.
		/*ServerSocket ss = null;
		int port = 61340;
		String host = "localhost";
		try {
			host = InetAddress.getLocalHost().getHostName();
		}
		catch(UnknownHostException e) {
			// Do nothing
		}
		if(args.length == 1) {
			try {
				port = Integer.parseInt(args[0]);
			}
			catch(Exception e) {
				System.err.println("Cannot use "+args[0]+" as a port number. Using default 61340.");
			}
		}
		try {
			ss = new ServerSocket(61340);
		}
		catch(Exception e) {
			System.out.println(e);
			System.exit(1);
		}
		System.out.println("ProgramServer started on "+host+":"+port);
		while(true) {
			try {
				Socket s=ss.accept();
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				String command=(String)ois.readObject();
				switch(command) {
						
				}
			}
			catch(Exception e) {
				System.out.print(e);
			}
		}*/
	}
}
