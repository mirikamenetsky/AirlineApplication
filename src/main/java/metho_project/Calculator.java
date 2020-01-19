package metho_project;

import java.time.LocalDateTime;

public class Calculator {

	public static LocalDateTime calculateArrivalTime(LocalDateTime dDate, int hours, int minutes) {
		return dDate.plusHours(hours).plusMinutes(minutes);
	}
}
