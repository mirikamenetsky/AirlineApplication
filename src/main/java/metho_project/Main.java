package metho_project;

import java.util.*;
import java.text.ParseException;
import java.time.LocalDate;

public class Main {

	public static List<Flight> upcomingFlights = new ArrayList<>();
	private static Menu menu = new Menu();
	private static InputOutput io = new InputOutput();
	private static Search search = new Search();
	
	public static void main(String[] args) throws ParseException {

		setInputPrintStreams();		
		InputOutput.printStream.println("---Welcome to Script Airlines---");
		int choice = 0;
		while (choice < 7) {
			choice = menu.chooseItemMain();
			executeOperation(choice);
		}
	}

	public static void setInputPrintStreams() {
		io.setInputStream(System.in);
		io.setPrintStream(System.out);

		menu.setInputStream(System.in);
		menu.setPrintStream(System.out);

		FlightModification fm = new FlightModification();
		fm.setInputStream(System.in);
		fm.setPrintStream(System.out);
		
		search.setPrintStream(System.out);
		SystemClock sc = new RealSystemClock();
		search.setSystemClock(sc);
	}

	public static void executeOperation(int choice) throws ParseException {

		switch (choice) {
		case 1:
			FlightModification.addFlights();
			break;
		case 2:
			Search.viewFlights(Main.upcomingFlights);
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
			InputOutput.printStream.println("That was not an available option");
		}
	}

	public static void cancelFlight() {
		boolean successful = FlightModification.cancelFlight();
		if(successful) {
			InputOutput.printStream.println("Flight removed successfully");
			}
		else {
			InputOutput.printStream.println("Flight not Found");
		}
	}

	public static void upcomingFlightsWithinOneWeek() {
		List<Flight> flights = search.upcomingFlightsWithinOneWeek();
		search.viewFlights(flights);
	}

	public static void modifyFlight() {
		int flightNum = FlightModification.getFlightNumFromUser();
		int index = Search.findFlight(flightNum);
		if (index == -1) {
			InputOutput.printStream.println("Flight does not exist");
			return;
		}
		int choice = menu.chooseItemToModify();
		FlightModification.executeModificationChoice(choice, index);
	}

	public static void searchForFlights() {
		int choice = menu.flightSearch();
		executeFlightSearchChoice(choice);
	}
	
	public static void executeFlightSearchChoice(int choice) {
		switch (choice) {
		case 1:
			searchByDeparture();
			break;
		case 2:
			searchByDestination();
			break;
		default:
			return;
		}
	}

	public static void searchByDestination() {
		LocalDate date = getDateFromUser();
		InputOutput.printStream.println("Enter Destination");
		String destination = InputOutput.scanner.nextLine();
		Search.searchByDestination(date, destination);
	}

	public static void searchByDeparture() {
		LocalDate date = getDateFromUser();
		InputOutput.printStream.println("Enter Departure Location");
		String departure = InputOutput.scanner.nextLine();
		Search.searchByDeparture(date, departure);
	}

	public static LocalDate getDateFromUser() {
		InputOutput.printStream.print("Enter date to view available flights (yyyy-MM-dd):");
		String d = InputOutput.scanner.nextLine();
		LocalDate date = LocalDate.parse(d);
		return date;
	}

}
