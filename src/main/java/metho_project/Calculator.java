package metho_project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Calculator {

	private SystemClock systemClock;

	public void setSystemClock(SystemClock systemClock) {
		this.systemClock = systemClock;
	}

	public List<Flight> upcomingFlights() {
		List<Flight> flights = new ArrayList<>();
		LocalDateTime today = systemClock.getCurrentDate();
		for (Flight flight : Main.upcomingFlights) {
			if (flight.getDepartureDate().isBefore(today.plusDays(7)))
				;
			flights.add(flight);
		}
		return flights;

	}
}
