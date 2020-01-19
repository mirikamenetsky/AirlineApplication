package metho_project;

import java.time.LocalDateTime;
import java.util.List;

import data.Flight;
import io.Prompter;
import validators.*;

public class FlightModification {
	private final Searcher search;
	private final Prompter prompter;
	private final StringValidator stringValidator;
	private final DateValidator dateValidator;
	private final List<Flight> flights;

	public FlightModification(Prompter prompter, StringValidator stringValidator, DateValidator dateValidator, Searcher search, List<Flight> flights) {
		this.search = search;
		this.prompter = prompter;
		this.stringValidator = stringValidator;
		this.dateValidator = dateValidator;
		this.flights = flights;
	}

	public boolean cancelFlight() {
		int flightNumber = getFlightNumFromUser();
		int removeIndex = search.findFlight(flightNumber);
		if (removeIndex >= 0) {
			flights.remove(removeIndex);
			return true;
		}
		return false;
	}

	public int getFlightNumFromUser() {
		int flightNum = prompter.promptFlight("Enter Flight Number:");
		return flightNum;
	}

	public void executeModificationChoice(int choice, int index) {
		switch (choice) {
		case 1:
			modifyDeparturePlace(index);
			break;
		case 2:
			modifyDestination(index);
			break;
		case 3:
			modifyDepartureDateTime(index);
			break;
		default:
			throw new IllegalArgumentException("Will never reach this line");
		}
	}

	private void modifyDepartureDateTime(int index) {

		LocalDateTime departureDate = prompter.promptDate("Enter new Departure Date and Time (yyyy-MM-dd HH:mm): ",
				dateValidator);
		LocalDateTime aDate = Calculator.calculateArrivalTime(departureDate, flights.get(index).getFlightHours(),
				flights.get(index).getFlightMinutes());
		flights.get(index).setDepartureDate(departureDate);
		flights.get(index).setArrivalDate(aDate);
	}

	private void modifyDestination(int index) {
		String destination = prompter.prompt("Destination:", stringValidator);

		int hours = prompter.prompt("Flight length hours:", 0, 21);

		int minutes = prompter.prompt("minutes:", 0, 59);

		LocalDateTime aDate = Calculator.calculateArrivalTime(flights.get(index).getDepartureDate(), hours, minutes);
		flights.get(index).setDestination(destination);
		flights.get(index).setFlightHours(hours);
		flights.get(index).setFlightMinutes(minutes);
		flights.get(index).setArrivalDate(aDate);
	}

	private void modifyDeparturePlace(int index) {
		String departure = prompter.prompt("Departing from:", stringValidator);

		int hours = prompter.prompt("Flight length hours:", 0, 21);

		int minutes = prompter.prompt("minutes:", 0, 59);

		LocalDateTime aDate = Calculator.calculateArrivalTime(flights.get(index).getDepartureDate(), hours, minutes);
		flights.get(index).setDeparture(departure);
		flights.get(index).setFlightHours(hours);
		flights.get(index).setFlightMinutes(minutes);
		flights.get(index).setArrivalDate(aDate);

	}
}
