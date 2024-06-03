package project.xyz;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DatePrint {
	
	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM dd, yyyy");
	private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
	
	public static String getDateString(LocalDate date) {
		String output = "-";
		if (date != null) {
			output = date.format(dateFormatter);
		}
		return output;
	}
	
	public static String getTimeString(LocalTime time) {
		String output = "-";
		if (time != null) {
			output = time.format(timeFormatter);
		}
		return output;
	}
}
