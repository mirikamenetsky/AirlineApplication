package metho_project;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import data.Flight;
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
		LocalDateTime today = systemClock.getCurrentDate();
		return flights.stream().filter(f -> f.getDepartureDate().isBefore(today.plusDays(7)) && f.getDepartureDate().isAfter(today)).collect(toList());
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
		List<Flight> list = flights.stream().filter(f -> f.getDeparture().equalsIgnoreCase(departure) && f.getDepartureDate().toLocalDate().equals(date)).collect(toList());
		viewFlights(list);
	}

	public void searchByDestination() {
		LocalDate date = getDateFromUser();
		String destination = prompter.prompt("Enter Destination", stringValidator);
		List<Flight> list = flights.stream().filter(f -> f.getDestination().equalsIgnoreCase(destination) && f.getDepartureDate().toLocalDate().equals(date)).collect(toList());
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
