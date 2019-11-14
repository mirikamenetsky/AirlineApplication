package metho_project;

import java.util.*;
import java.text.ParseException;


public class Main {

	public static void main(String[] args) throws ParseException {

		Scanner keyboard = new Scanner(System.in);

		// create array list to store the flights
		ArrayList<Flight> flights = new ArrayList<Flight>();
		System.out.println("---Welcome to Script Airlines---");
		int choice;
		do {
			choice = menu(keyboard);
			switch (choice) {
			case 1:
				addFlights(keyboard, flights);
				break;
			case 2:
				viewFlights(flights);
				break;
			case 3:
				System.exit(0);
			default:
				System.out.println("That was not an available option");
			}
		} while (choice == 1 || choice == 2);
	}

	public static void viewFlights(ArrayList<Flight> flights) {
		System.out.println("---Available Flights---");
		for (Flight temp : flights) {
			System.out.println(temp);
		}
	}

	public static int menu(Scanner keyboard) {
		System.out.println("Please select one of the following options");
		System.out.println("1. Add Flights");
		System.out.println("2. View Available Flights");
		System.out.println("3. Exit");
		int choice = keyboard.nextInt();
		// clear buffer
		keyboard.nextLine();
		return choice;
	}

	public static void addFlights(Scanner keyboard, ArrayList<Flight> flights) throws ParseException {
		char again = 'Y';

		while (again == 'Y') {
			System.out.print("Departing from:");
			String departure = keyboard.nextLine();

			System.out.print("Departure Date and Time (MM/dd/yyyy HH:mm): ");
			String departureDate = keyboard.nextLine();

			System.out.print("Destination:");
			String destination = keyboard.nextLine();

			System.out.print("Flight length (HH:MM):");
			String flightLength = keyboard.nextLine();

			System.out.print("Maximum Passengers");
			int maxPass = keyboard.nextInt();
			// clear the buffer
			keyboard.nextLine();

			// add the new flight to the flight list
			flights.add(new Flight(departure, destination, maxPass, departureDate, flightLength));
			System.out.println("Would you like to schedule another flight? Y or N");
			String input = keyboard.nextLine().toUpperCase();
			again = input.charAt(0);
		}
	}

}
