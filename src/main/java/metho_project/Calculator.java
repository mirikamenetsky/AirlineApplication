package metho_project;

import java.time.LocalDateTime;

public class Calculator {

	public  LocalDateTime calculateArrivalTime(LocalDateTime dDate, int hours, int minutes) {
		dDate = dDate.plusHours(hours).plusMinutes(minutes);
		return dDate;
	}
}
