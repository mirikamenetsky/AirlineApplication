package metho_project;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Search {

	private SystemClock systemClock;
	private PrintStream printStream;
	private Formatter formatter;
	private StringPrompter stringPrompter;
	private StringValidator stringValidator;
	private DatePrompter datePrompter;
	private DateValidator dateValidator;

	public Search(StringPrompter stringPrompter, SystemClock systemClock, PrintStream printStream, Formatter formatter, StringValidator stringValidator, DatePrompter datePrompter, DateValidator dateValidator) {
		this.systemClock = systemClock;
		this.printStream = printStream;
		this.formatter = formatter;
		this.stringPrompter = stringPrompter;
		this.stringValidator = stringValidator;
		this.datePrompter = datePrompter;
		this.dateValidator = dateValidator;
	}

	public List<Flight> upcomingFlightsWithinOneWeek() {
		List<Flight> flights = new ArrayList<>();
		LocalDateTime today = systemClock.getCurrentDate();
		for (Flight flight : Main.upcomingFlights) {
			if (flight.getDepartureDate().isBefore(today.plusDays(7)) && flight.getDepartureDate().isAfter(today.minusDays(1))) {
			flights.add(flight);
			}
		}
		return flights;
	}

	public int findFlight(int flightNumber) {
		for (int i = 0; i < Main.upcomingFlights.size(); i++) {
			if (Main.upcomingFlights.get(i).getFlightNumber() == flightNumber) {
				return i;
			}
		}
		return -1;
	}

	public void searchByDeparture() {
		LocalDate date = getDateFromUser();
		String departure = stringPrompter.promptUser("Enter Departure Location", stringValidator);
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

	public void searchByDestination() {
		LocalDate date = getDateFromUser();
		String destination = stringPrompter.promptUser("Enter Destination", stringValidator);
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

	public void viewFlights(List<Flight> flights) {
		if (flights.isEmpty()) {
			printStream.println("---No flights to display---");
			return;
		}
		printStream.println("---Available Flights---");
		for (Flight temp : flights) {
			if (temp.getDepartureDate().isAfter(systemClock.getCurrentDate())) {
				printStream.println(formatter.flightDisplayer(temp));
			} 
		}
	}
	
	public LocalDate getDateFromUser() {
		LocalDateTime d = datePrompter.promptUser("Enter date to view available flights (yyyy-MM-dd 00:00):", dateValidator);
		LocalDate date = d.toLocalDate();
		return date;
	}
}
