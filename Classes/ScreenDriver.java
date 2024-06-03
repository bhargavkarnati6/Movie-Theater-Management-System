package project.xyz;

import java.io.*;
import java.util.Scanner;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class ScreenDriver {
	public static void main (String[] args) throws FileNotFoundException {
		Screen screen1 = null;
		Screen screen2 = null;
		Screen screen3 = null;
		try {
			String filePath = "";
			File fileIn = new File(filePath);
			Scanner reader = new Scanner(fileIn);
			while (reader.hasNext()) {
				String line = reader.nextLine();
				Scanner lineReader = new Scanner(line);
				lineReader.useDelimiter(",");
				String text = lineReader.next();
				String textNoQuote = text.substring(1, text.length()-1);
				if (textNoQuote.equals("screenId")) {
					text = lineReader.next();
					textNoQuote = text.substring(1, text.length()-1);
					int screenId = Integer.parseInt(textNoQuote);
					text = lineReader.next();
					textNoQuote = text.substring(1, text.length()-1);
					String screenType = textNoQuote;
					text = lineReader.next();
					textNoQuote = text.substring(1, text.length()-1);
					double basePrice = Double.parseDouble(textNoQuote);
					text = lineReader.next();
					textNoQuote = text.substring(1, text.length()-1);
					int capacity = Integer.parseInt(textNoQuote);
					Seat[] layout = new Seat[capacity];
					for (int i=0; i<capacity; i++) {
						line = reader.nextLine();
						lineReader = new Scanner(line);
						lineReader.useDelimiter(",");
						text = lineReader.next();
						textNoQuote = text.substring(1, text.length()-1);
						String seatId = textNoQuote;
						text = lineReader.next();
						textNoQuote = text.substring(1, text.length()-1);
						String seatType = textNoQuote;
						text = lineReader.next();
						textNoQuote = text.substring(1, text.length()-1);
						double additionalPrice = Double.parseDouble(textNoQuote);
						Seat toAdd = new Seat(seatId, seatType, additionalPrice);
						layout[i] = toAdd;
					}
					if (screenId==1) {
						screen1 = new Screen(screenId, screenType, basePrice, layout);
					}
					else if (screenId==2) {
						screen2 = new Screen(screenId, screenType, basePrice, layout);
					}
					else if (screenId==3) {
						screen3 = new Screen(screenId, screenType, basePrice, layout);
					}
				}
				lineReader.close();
			}
			reader.close();
			System.out.println(screen1.printAllSeat());
			System.out.println(screen2.printAllSeat());
			System.out.println(screen3.printAllSeat());
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		Showtime showtime = null;
		try {
			String filePath = "/home1/ugrads/t9mju/CS2043/backup/schedule.csv";
			File fileIn = new File(filePath);
			Scanner reader = new Scanner(fileIn);
			while (reader.hasNext()) {
				String line = reader.nextLine();
				Scanner lineReader = new Scanner(line);
				lineReader.useDelimiter(",");
				String text = lineReader.next();
				String textNoQuote = text.substring(1, text.length()-1);
				LocalDate date = null;
				LocalTime time = null;
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				int screenNum = 0;
				if (textNoQuote.equals("date")) {
					text = lineReader.next();
					textNoQuote = text.substring(1, text.length()-1);
					date = LocalDate.parse(textNoQuote, dateFormatter);
					text = lineReader.next();
					textNoQuote = text.substring(1, text.length()-1);
					time = LocalTime.parse(textNoQuote);
					text = lineReader.next();
					textNoQuote = text.substring(1, text.length()-1);
					screenNum = Integer.parseInt(textNoQuote);
					showtime = new Showtime(date, time);
					for (int i=0; i<screenNum; i++) {
						line = reader.nextLine();
						lineReader = new Scanner(line);
						lineReader.useDelimiter(",");
						text = lineReader.next();
						textNoQuote = text.substring(1, text.length()-1);
						String screen = textNoQuote;
						text = lineReader.next();
						textNoQuote = text.substring(1, text.length()-1);
						String movie = textNoQuote;
						if (screen.equals("screen1")) {
							showtime.addShow(screen1, movie);
						}
						else if (screen.equals("screen2")) {
							showtime.addShow(screen2, movie);
						}
						else if (screen.equals("screen3")) {
							showtime.addShow(screen3, movie);
						}
					}
					System.out.println(showtime.printProgram());
				}
				lineReader.close();
			}
			reader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
