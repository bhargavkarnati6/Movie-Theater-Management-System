package project.xyz;

import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class ReadInput {
	
	//The directory of the input files.
	private static String directory = "//";
	//The date format of the inputs.
	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	//The time format of the inputs.
	private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	
	public static String noQuote(String text) {
		return text.substring(1, text.length()-1);
	}
	
	public static ArrayList<Screen> availableScreen() throws FileNotFoundException {
		ArrayList<Screen> output = new ArrayList<>();
		String filePath = directory+"layout.csv";
		File fileIn = new File(filePath);
		Scanner reader = new Scanner(fileIn);
		while (reader.hasNext()) {
			String line = reader.nextLine();
			Scanner lineReader = new Scanner(line);
			lineReader.useDelimiter(",");
			String text = lineReader.next();
			if (noQuote(text).equals("screenId")) {
				text = lineReader.next();
				int screenId = Integer.parseInt(noQuote(text));
				text = lineReader.next();
				String screenType = noQuote(text);
				text = lineReader.next();
				double basePrice = Double.parseDouble(noQuote(text));
				text = lineReader.next();
				int capacity = Integer.parseInt(noQuote(text));
				Seat[] layout = new Seat[capacity];
				for (int i=0; i<capacity; i++) {
					line = reader.nextLine();
					lineReader = new Scanner(line);
					lineReader.useDelimiter(",");
					text = lineReader.next();
					String seatId = noQuote(text);
					text = lineReader.next();
					String seatType = noQuote(text);
					text = lineReader.next();
					double additionalPrice = Double.parseDouble(noQuote(text));
					Seat toAdd = new Seat(seatId, seatType, additionalPrice);
					layout[i] = toAdd;
				}
				Screen screen = new Screen(screenId, screenType, basePrice, layout);
				output.add(screen);
			}
			lineReader.close();
		}
		reader.close();
		return output;
	}
	
	public static ArrayList<Showtime> availableShowtime() throws FileNotFoundException, Exception {
		ArrayList<Showtime> output = new ArrayList<>();
		String filePath = directory+"schedule.csv";
		File fileIn = new File(filePath);
		Scanner reader = new Scanner(fileIn);
		while (reader.hasNext()) {
			String line = reader.nextLine();
			Scanner lineReader = new Scanner(line);
			lineReader.useDelimiter(",");
			String text = lineReader.next();
			if (noQuote(text).equals("date")) {
				text = lineReader.next();
				LocalDate date = LocalDate.parse(noQuote(text), dateFormatter);
				text = lineReader.next();
				LocalTime time = LocalTime.parse(noQuote(text), timeFormatter);
				text = lineReader.next();
				int screenNum = Integer.parseInt(noQuote(text));
				Showtime showtime = new Showtime(date, time);
				ArrayList<Screen> screens = availableScreen();
				ArrayList<Movie> movies = availableMovie();
				for (int i=0; i<screenNum; i++) {
					line = reader.nextLine();
					lineReader = new Scanner(line);
					lineReader.useDelimiter(",");
					text = lineReader.next();
					int screenId = Integer.parseInt(noQuote(text));
					int screenIndex = screenId-1;
					text = lineReader.next();
					String movie = noQuote(text);
					boolean movieFound = false;
					Movie selectedMovie = null;
					for (int j=0; j<movies.size(); j++) {
						if (movie.equals(movies.get(j).getTitle())) {
							selectedMovie = movies.get(j);
							movieFound = true;
							break;
						}
					}
					lineReader.close();
					if (!movieFound) {
						throw new Exception("The Movie "+movie+" is not available in the database");
					}
					else {
						showtime.addShow(screens.get(screenIndex), selectedMovie);
					}
				}
				output.add(showtime);
			}
			lineReader.close();
		}
		reader.close();
		return output;
	}
	
	public static ArrayList<Movie> availableMovie() throws FileNotFoundException {
		ArrayList<Movie> output = new ArrayList<>();
		String filePath = directory+"movie_list.csv";
		File fileIn = new File(filePath);
		Scanner reader = new Scanner(fileIn);
		String line = reader.nextLine();
		while (reader.hasNext()) {
			line = reader.nextLine();
			Scanner lineReader = new Scanner(line);
			lineReader.useDelimiter(",");
			String text = lineReader.next();
			String title = noQuote(text);
			text = lineReader.next();
			String genre = noQuote(text);
			text = lineReader.next();
			String language = noQuote(text);
			text = lineReader.next();
			LocalDate released = LocalDate.parse(noQuote(text), dateFormatter);
			text = lineReader.next();
			String description = noQuote(text);
			Movie movie = new Movie(title, genre, language, released, description);
			output.add(movie);
			lineReader.close();
		}
		reader.close();
		return output;
	}
	
	public static ArrayList<User> userList() throws IOException, ClassNotFoundException {
		ArrayList<User> output = new ArrayList<>();
		String filePath = directory+"user_list.dat";
		FileInputStream fileIn = new FileInputStream(filePath);
		ObjectInputStream objectIn = new ObjectInputStream(fileIn);
		User user = (User)objectIn.readObject();
		while (!user.getEmail().equals("dummy")) {
			output.add(0, user);
			user = (User)objectIn.readObject();
		}
		objectIn.close();
		fileIn.close();
		return output;
	}
}
