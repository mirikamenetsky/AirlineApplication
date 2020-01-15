package metho_project;

import java.util.List;
import io.Prompter;
import validators.RepeatValidator;

public class Program {

	private final Menu menu;
	private final Searcher searcher;
	private final FlightModification fm;
	private final Prompter prompter;
	private final RepeatValidator repeatValidator;
	private final FlightEntryForm fef;
	private final List<Flight> flights;

	public Program(Menu menu, Searcher searcher, FlightModification fm, Prompter prompter,
			RepeatValidator repeatValidator, FlightEntryForm fef, List<Flight> flights) {
		this.menu = menu;
		this.searcher = searcher;
		this.fm = fm;
		this.prompter = prompter;
		this.repeatValidator = repeatValidator;
		this.fef = fef;
		this.flights = flights;

	}

	public void loop() {
		int choice;
		while ((choice = menu.chooseItemMain()) != 7) {
			executeOperation(choice);
		}
	}

	public void executeOperation(int choice) {

		switch (choice) {
		case 1:
			addFlights();
			break;
		case 2:
			searcher.viewFlights(flights);
			break;
		case 3:
			cancelFlight();
			break;
		case 4:
			modifyFlight();
			break;
		case 5:
			searchForFlights();
			break;
		case 6:
			upcomingFlightsWithinOneWeek();
			break;
		case 7:
			return;
		default:
			throw new IllegalArgumentException("Will never reach this line");
		}
	}

	public void addFlights() {
		String input = "Y";
		while (input.equals("Y") || input.equals("YES")) {
			Flight flight = fef.createFlight();
			flights.add(flight);
			input = prompter.prompt("Would you like to schedule another flight? Y or N", repeatValidator).toUpperCase();
		}

	}

	public void cancelFlight() {
		boolean successful = fm.cancelFlight();
		if (successful) {
			prompter.println("Flight removed successfully");
		} else {
			prompter.println("Flight not Found");
		}
	}

	public void upcomingFlightsWithinOneWeek() {
		List<Flight> flights = searcher.upcomingFlightsWithinOneWeek();
		searcher.viewFlights(flights);
	}

	public void modifyFlight() {
		int flightNum = fm.getFlightNumFromUser();
		int index = searcher.findFlight(flightNum);
		if (index == -1) {
			prompter.println("Flight does not exist");
			return;
		}
		int choice = menu.chooseItemToModify();
		fm.executeModificationChoice(choice, index);
		prompter.println("Flight modified successfully");
	}

	public void searchForFlights() {
		int choice = menu.chooseFlightsToSearch();
		executeFlightSearchChoice(choice);
	}

	public void executeFlightSearchChoice(int choice) {
		switch (choice) {
		case 1:
			searcher.searchByDeparture();
			break;
		case 2:
			searcher.searchByDestination();
			break;
		default:
			throw new IllegalArgumentException("Will never reach this line");
		}
	}

}
