package metho_project;

import java.util.*;
import java.text.ParseException;

public class Main {

	public static List<Flight> flights = new ArrayList<>();

	public static void main(String[] args) throws ParseException {
		
		
		InputOutput io = new InputOutput();
		io.setInputStream(System.in);
		io.setPrintStream(System.out);

		io.printStream.println("---Welcome to Script Airlines---");

		
		Menu menu = new Menu();

		int choice = 0;
		while (choice < 8) {
			choice = menu.chooseItemMainMenu();
			executeOperation(choice, io);
		}

	}

	public static void executeOperation(int choice, InputOutput io) {
		
		switch (choice) {
		case 1:
			FlightModification.addFlights();
			break;
		case 2:
			Flight.viewFlights();
			break;
		case 3:
			FlightModification.cancelFlight();
			break;
		case 4:
			FlightModification.modifyFlight();
			break;
		case 5:
			flightSearch();
			break;
		case 6:
			displayUpcomingFlights();
			break;
		case 7:
			return;
		default:
			io.printStream.println("That was not an available option");
		}

	}

}
