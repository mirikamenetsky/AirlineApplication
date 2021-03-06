package metho_project;

import java.time.LocalDateTime;

import data.Flight;
import io.Prompter;
import validators.DateValidator;
import validators.StringValidator;

public class FlightEntryForm {

	private final Prompter prompter;
	private final StringValidator stringValidator;
	private final DateValidator dateValidator;

	public FlightEntryForm(Prompter prompter, StringValidator stringValidator, DateValidator dateValidator) {
		this.prompter = prompter;
		this.stringValidator = stringValidator;
		this.dateValidator = dateValidator;
	}

	public Flight createFlight() {
		String departure = prompter.prompt("\nDeparting from:", stringValidator);

		LocalDateTime departureDate = prompter.promptDate("Departure Date and Time (yyyy-MM-dd HH:mm): ",
				dateValidator);

		String destination = prompter.prompt("Destination:", stringValidator);

		int flightLengthHours = prompter.prompt("Flight length hours:", 0, 21);

		int flightLengthMin = prompter.prompt("Flight length minutes:", 0, 59);

		// we checked the max passengers possible for a plane with normal four class
		// configuration
		int maxPass = prompter.prompt("Maximum Passengers", 0, 544);

		LocalDateTime arriveDate = Calculator.calculateArrivalTime(departureDate, flightLengthHours, flightLengthMin);

		return new Flight(departure, destination, maxPass, departureDate, flightLengthHours, flightLengthMin,
				arriveDate);
	}
}
