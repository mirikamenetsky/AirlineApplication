package metho_project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.*;
import validators.*;

public class Searcher {

	private final SystemClock systemClock;
	private final Formatter formatter;
	private final StringValidator stringValidator;
	private final DateValidator dateValidator;
	private final Prompter prompter;
	private final List<Flight> flights;

	public Searcher(Prompter prompter, SystemClock systemClock, Formatter formatter, StringValidator stringValidator,
			DateValidator dateValidator, List<Flight> flights) {
		this.prompter = prompter;
		this.systemClock = systemClock;
		this.formatter = formatter;
		this.stringValidator = stringValidator;
		this.dateValidator = dateValidator;
		this.flights = flights;
	}

	public List<Flight> upcomingFlightsWithinOneWeek() {
		List<Flight> flightList = new ArrayList<>();
		LocalDateTime today = systemClock.getCurrentDate();
		for (Flight flight : flights) {
			if (flight.getDepartureDate().isBefore(today.plusDays(7)) && flight.getDepartureDate().isAfter(today)) {
				flightList.add(flight);
			}
		}
		return flightList;
	}

	public int findFlight(int flightNumber) {
		for (int i = 0; i < flights.size(); i++) {
			if (flights.get(i).getFlightNumber() == flightNumber) {
				return i;
			}
		}
		return -1;
	}

	public void searchByDeparture() {
		LocalDate date = getDateFromUser();
		String departure = prompter.prompt("Enter Departure Location", stringValidator);
		List<Flight> list = flights;
		list = list.stream().filter(f -> ((Flight) f).getDeparture().equalsIgnoreCase(departure)
				&& ((Flight) f).getDepartureDate().toLocalDate().equals(date)).collect(Collectors.toList());
		viewFlights(list);
	}

	public void searchByDestination() {
		LocalDate date = getDateFromUser();
		String destination = prompter.prompt("Enter Destination", stringValidator);
		List<Flight> list = flights;
		list = list.stream().filter(f -> ((Flight) f).getDestination().equalsIgnoreCase(destination)
				&& ((Flight) f).getDepartureDate().toLocalDate().equals(date)).collect(Collectors.toList());
		viewFlights(list);
	}

	public void viewFlights(List<Flight> flights) {
		if (flights.isEmpty()) {
			prompter.println("\n---No flights to display---");
			return;
		}
		prompter.println("\n---Available Flights---");
		for (Flight temp : flights) {
			if (temp.getDepartureDate().isAfter(systemClock.getCurrentDate())) {
				prompter.println(formatter.flightDisplayer(temp));
			}
		}
	}

	public LocalDate getDateFromUser() {
		LocalDateTime d = prompter.promptDate("Enter date to view available flights (yyyy-MM-dd 00:00):",
				dateValidator);
		LocalDate date = d.toLocalDate();
		return date;
	}
}
