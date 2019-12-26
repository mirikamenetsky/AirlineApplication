package metho_project;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Search {
	
	private static SystemClock systemClock;
	private static PrintStream printStream;
	
	public void setPrintStream(PrintStream printStream) {
		this.printStream = printStream;
	}

	public void setSystemClock(SystemClock systemClock) {
		this.systemClock = systemClock;
	}

	public List<Flight> upcomingFlightsWithinOneWeek() {
		List<Flight> flights = new ArrayList<>();
		LocalDateTime today = systemClock.getCurrentDate();
		for (Flight flight : Main.upcomingFlights) {
			if (flight.getDepartureDate().isBefore(today.plusDays(7)) && flight.getDepartureDate().isAfter(today));
			flights.add(flight);
		}
		return flights;
	}
	
	public static int findFlight(int flightNumber) {
		for (int i = 0; i < Main.upcomingFlights.size(); i++) {
			if (Main.upcomingFlights.get(i).getFlightNumber() == flightNumber) {
				return i;
			}
		}
		return -1;
	}

	public static void searchByDeparture(LocalDate date, String departure) {
		List<Flight> flights = new ArrayList<>();
		for (Flight f : Main.upcomingFlights) {
			LocalDate departingDate = f.getDepartureDate().toLocalDate();
			if (departingDate.equals(date)) {
				if (departure.equalsIgnoreCase(f.getDeparture())) {
					flights.add(f);
				}
			}
		}
		viewFlights(flights);
	}
	
	public static void searchByDestination(LocalDate date, String destination) {
		List<Flight> flights = new ArrayList<>();
		for (Flight f : Main.upcomingFlights) {
			LocalDate departingDate = f.getDepartureDate().toLocalDate();
			if (departingDate.equals(date)) {
				if (destination.equalsIgnoreCase(f.getDestination())) {
					flights.add(f);
				}
			}
		}
		viewFlights(flights);
	}
	
	public static void viewFlights(List<Flight> flights) {
		if (flights.isEmpty()) {
			printStream.println("---No flights to display---");
			return;
		}
		printStream.println("---Available Flights---");
		for (Flight temp : flights) {
			if(temp.getDepartureDate().isAfter(systemClock.getCurrentDate())) {
			printStream.println(Formatter.flightDisplayer(temp));
			}
			else {
				continue;
			}
		}
	}
}
