package metho_project;

import java.text.ParseException;
import java.time.LocalDateTime;

public class FlightModification {
	private Calculator calc;
	private Search search;
	private StringPrompter stringPrompter;
	private IntPrompter intPrompter;
	private DatePrompter datePrompter;
	private StringValidator stringValidator;
	private IntValidator intValidator;
	private DateValidator dateValidator;

	public FlightModification(StringPrompter stringPrompter, StringValidator stringValidator, DatePrompter datePrompter,
			DateValidator dateValidator, IntPrompter intPrompter, IntValidator intValidator, Calculator calc,
			Search search) {
		this.calc = calc;
		this.search = search;
		this.stringPrompter = stringPrompter;
		this.stringValidator = stringValidator;
		this.intPrompter = intPrompter;
		this.intValidator = intValidator;
		this.datePrompter = datePrompter;
		this.dateValidator = dateValidator;
	}

	public void addFlights() throws ParseException {

		char again = 'Y';
		while (again == 'Y') {

			String departure = stringPrompter.promptUser("Departing from:", stringValidator);

			LocalDateTime departureDate = datePrompter.promptUser("Departure Date and Time (yyyy-MM-dd HH:mm): ",
					dateValidator);

			String destination = stringPrompter.promptUser("Destination:", stringValidator);

			int flightLengthHours = intPrompter.promptUser("Flight length hours:", intValidator);

			int flightLengthMin = intPrompter.promptUser("Flight length minutes:", intValidator);

			int maxPass = intPrompter.promptUser("Maximum Passengers", intValidator);

			LocalDateTime arriveDate = calc.calculateArrivalTime(departureDate, flightLengthHours, flightLengthMin);

			Main.upcomingFlights.add(new Flight(departure, destination, maxPass, departureDate, flightLengthHours,
					flightLengthMin, arriveDate));

			String input = stringPrompter
					.promptUser("Would you like to schedule another flight? Y or N", stringValidator).toUpperCase();
			again = input.charAt(0);
			while (again != 'N' && again != 'Y') {
				input = stringPrompter.promptUser("Would you like to schedule another flight? Y or N", stringValidator)
						.toUpperCase();
				again = input.charAt(0);
			}
		}
	}

	public boolean cancelFlight() {
		int flightNumber = getFlightNumFromUser();
		int removeIndex = search.findFlight(flightNumber);
		if (removeIndex >= 0) {
			Main.upcomingFlights.remove(removeIndex);
			return true;
		}
		return false;
	}

	public int getFlightNumFromUser() {
		int flightNum = intPrompter.promptUser("Enter Flight Number:", intValidator);
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
			return;
		}
	}

	private void modifyDepartureDateTime(int index) {

		LocalDateTime departureDate = datePrompter.promptUser("Enter new Departure Date and Time (yyyy-MM-dd HH:mm): ",
				dateValidator);
		LocalDateTime aDate = calc.calculateArrivalTime(departureDate, Main.upcomingFlights.get(index).getFlightHours(),
				Main.upcomingFlights.get(index).getFlightMinutes());
		Main.upcomingFlights.get(index).setDepartureDate(departureDate);
		Main.upcomingFlights.get(index).setArrivalDate(aDate);
	}

	private void modifyDestination(int index) {
		String destination = stringPrompter.promptUser("Destination:", stringValidator);

		int hours = intPrompter.promptUser("Flight length hours:", intValidator);

		int minutes = intPrompter.promptUser("minutes:", intValidator);

		LocalDateTime aDate = calc.calculateArrivalTime(Main.upcomingFlights.get(index).getDepartureDate(), hours,
				minutes);
		Main.upcomingFlights.get(index).setDestination(destination);
		Main.upcomingFlights.get(index).setFlightHours(hours);
		Main.upcomingFlights.get(index).setFlightMinutes(minutes);
		Main.upcomingFlights.get(index).setArrivalDate(aDate);
	}

	private void modifyDeparturePlace(int index) {
		String departure = stringPrompter.promptUser("Departing from:", stringValidator);

		int hours = intPrompter.promptUser("Flight length hours:", intValidator);

		int minutes = intPrompter.promptUser("minutes:", intValidator);

		LocalDateTime aDate = calc.calculateArrivalTime(Main.upcomingFlights.get(index).getDepartureDate(), hours,
				minutes);
		Main.upcomingFlights.get(index).setDeparture(departure);
		Main.upcomingFlights.get(index).setFlightHours(hours);
		Main.upcomingFlights.get(index).setFlightMinutes(minutes);
		Main.upcomingFlights.get(index).setArrivalDate(aDate);

	}
}
