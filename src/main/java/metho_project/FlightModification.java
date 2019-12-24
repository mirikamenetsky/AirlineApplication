package metho_project;

import java.io.InputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class FlightModification {
	
	private static Menu menu = new Menu();

	public static void addFlights() throws ParseException {
		Scanner scanner = new Scanner(InputOutput.inputStream);
		char again = 'Y';

		while (again == 'Y') {
			// test this with a mock - if prompt was this, enter this
			// and at the very end, assert that you got the right object
			printStream.print("Departing from:");
			String departure = scanner.nextLine();

			printStream.print("Departure Date and Time (yyyy-MM-dd HH:mm): ");
			String departureDate = scanner.nextLine();

			printStream.print("Destination:");
			String destination = scanner.nextLine();

			printStream.print("Flight length hours:");
			int flightLengthHours = scanner.nextInt();
			printStream.print("minutes:");
			int flightLengthMin = scanner.nextInt();

			printStream.print("Maximum Passengers");
			int maxPass = scanner.nextInt();

			// clear the buffer
			scanner.nextLine();

			// parse user input
			LocalDateTime dDate = Formatter.parse(departureDate);
			LocalDateTime aDate = Flight.calculateArrivalTime(dDate, flightLengthHours, flightLengthMin);

			// add the new flight to the flight list
			Main.flights
					.add(new Flight(departure, destination, maxPass, dDate, flightLengthHours, flightLengthMin, aDate));
			System.out.println("Would you like to schedule another flight? Y or N");
			String input = scanner.nextLine().toUpperCase();
			again = input.charAt(0);
		}
	}

	public static void cancelFlight() {
		printStream.println("Enter flight number you wish to cancel:");
		int flightNumber = scanner.nextInt();
		scanner.nextLine();
		int removeIndex = findFlight(flightNumber); 
		if(removeIndex >= 0 ) {
			Main.flights.remove(removeIndex);
			printStream.println("Flight removed successfully.");
		}
		else {
			printStream.println("Flight not Found");
		}
	}
	public static int getFlightFromUser() {
		printStream.println("Enter Flight");
		int flightNum = scanner.nextInt();
		scanner.nextLine();
		return flightNum;
	}
	public static void modifyFlight() {
		int flightNum = getFlightFromUser();
		int index = findFlight(flightNum);
		if(index == -1) {
			outputStream.println("Flight does not exist");
			return;
		}
		int choice = menu.chooseItemToModify();
		executeModificationChoice(choice, index)
	}
	
	public static void executeModificationChoice(int choice, int index) {
		switch(choice) {
		case 1:
			modifyDepartureDateTime(index);
			break;
		case 2: 
			modifyDeparturePlace()
			break;
		case 3:
			modifyDestination(index);
			break;
		default:
			printStream.println("That was not an available option");
		}
	}

	private static void modifyDepartureDateTime(int index) {
		printStream.print("Departure Date and Time (yyyy-MM-dd HH:mm): ");
		String departureDate = scanner.nextLine();
		LocalDateTime dDate = Formatter.parse(departureDate);
		LocalDateTime aDate = Flight.calculateArrivalTime(dDate, Main.flights.get(index).getFlightHours(), Main.flights.get(index).getFlightMinutes());
		Main.flights.get(index).setDepartureDate(dDate);
		Main.flights.get(index).setArrivalDate(aDate);
	}

	private static void modifyDestination(int index) {
		printStream.print("Destination:");
		String destination = scanner.nextLine();
		printStream.print("Flight length hours:");
		int hours = scanner.nextInt();
		printStream.print("minutes:");
		int minutes = scanner.nextInt();
		scanner.nextLine();
		LocalDateTime aDate = Flight.calculateArrivalTime(Main.flights.get(index).getDepartureDate(), hours, minutes);
		Main.flights.get(index).setDestination(destination);
		Main.flights.get(index).setFlightHours(hours);
		Main.flights.get(index).setFlightMinutes(minutes);
		Main.flights.get(index).setArrivalDate(aDate);
				
	}

	private static void modifyDeparturePlace(int index) {
		printStream.print("Departing from:");
		String departure = scanner.nextLine();
		printStream.print("Flight length hours:");
		int hours = scanner.nextInt();
		printStream.print("minutes:");
		int minutes = scanner.nextInt();
		scanner.nextLine();
		LocalDateTime aDate = Flight.calculateArrivalTime(Main.flights.get(index).getDepartureDate(), hours, minutes);
		Main.flights.get(index).setDeparture(departure);
		Main.flights.get(index).setFlightHours(hours);
		Main.flights.get(index).setFlightMinutes(minutes);
		Main.flights.get(index).setArrivalDate(aDate);
		
	}

	public static int findFlight(int flightNumber) {
		for (int i = 0; i < Main.flights.size(); i++) {
			if (Main.flights.get(i).getFlightNumber() == flightNumber) {
				return i;
			}
		}
		return -1;
	}

}
