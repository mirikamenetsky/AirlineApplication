package metho_project;

import java.util.*;
import java.text.ParseException;
import java.time.LocalDate;

public class Main {

	public static List<Flight> upcomingFlights = new ArrayList<>();
	public static List<Flight> pastFlights = new ArrayList<>();

	public static void main(String[] args) throws ParseException {

		InputOutput io = new InputOutput();
		io.setInputStream(System.in);
		io.setPrintStream(System.out);

		io.printStream.println("---Welcome to Script Airlines---");

		Menu menu = new Menu();
		menu.setInputStream(System.in);
		menu.setPrintStream(System.out);

		FlightModification fm = new FlightModification();
		fm.setInputStream(System.in);
		fm.setPrintStream(System.out);

		Flight flight = new Flight();
		flight.setInputStream(System.in);
		flight.setPrintStream(System.out);

		int choice = 0;
		while (choice < 8) {
			choice = menu.chooseItemMain();
			executeOperation(choice, io, menu);
		}

	}

	public static void executeOperation(int choice, InputOutput io, Menu menu) throws ParseException {

		switch (choice) {
		case 1:
			FlightModification.addFlights();
			break;
		case 2:
			Flight.viewFlights(Main.upcomingFlights);
			break;
		case 3:
			FlightModification.cancelFlight();
			break;
		case 4:
			modifyFlight(menu, io);
			break;
		case 5:
			searchForFlights(menu, io);
			break;
		case 6:
			SystemClock clock = new RealSystemClock();
			Flight.displayUpcomingFlights(clock);
			break;
		case 7:
			return;
		default:
			io.printStream.println("That was not an available option");
		}

	}

	public static void modifyFlight(Menu menu, InputOutput io) {
		int flightNum = FlightModification.getFlightFromUser();
		int index = FlightModification.findFlight(flightNum);
		if (index == -1) {
			io.printStream.println("Flight does not exist");
			return;
		}
		int choice = menu.chooseItemToModify();
		FlightModification.executeModificationChoice(choice, index);
	}

	public static void searchForFlights(Menu menu, InputOutput io) {
		Scanner scanner = new Scanner(io.inputStream);
		io.printStream.println("Enter date to view available flights");
		String d = scanner.nextLine();
		LocalDate date = LocalDate.parse(d);
		int choice = menu.flightSearch();
		Flight.executeFlightSearchChoice(choice, date);
	}

}
