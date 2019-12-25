package metho_project;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlightModification {
	
	private static InputStream inputStream;
	private static PrintStream printStream;

	
	
	public void setPrintStream(PrintStream printStream) {
		this.printStream = printStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

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
			Main.upcomingFlights
					.add(new Flight(departure, destination, maxPass, dDate, flightLengthHours, flightLengthMin, aDate));
			System.out.println("Would you like to schedule another flight? Y or N");
			String input = scanner.nextLine().toUpperCase();
			again = input.charAt(0);
		}
	}

	public static void cancelFlight() {
		Scanner scanner = new Scanner(inputStream);
		printStream.println("Enter flight number you wish to cancel:");
		int flightNumber = scanner.nextInt();
		scanner.nextLine();
		int removeIndex = findFlight(flightNumber); 
		if(removeIndex >= 0 ) {
			Main.upcomingFlights.remove(removeIndex);
			printStream.println("Flight removed successfully.");
		}
		else {
			printStream.println("Flight not Found");
		}
	}
	public static int getFlightFromUser() {
		Scanner scanner = new Scanner(inputStream);
		printStream.println("Enter Flight");
		int flightNum = scanner.nextInt();
		scanner.nextLine();
		return flightNum;
	}
	
	public static void executeModificationChoice(int choice, int index) {
		modifyDepartureDateTime(index);
		switch(choice) {
		case 1:
			modifyDestination(index);
			break;
		case 2: 
			modifyDeparturePlace(index);
			break;
		default:
			printStream.println("That was not an available option");
		}
	}

	private static void modifyDepartureDateTime(int index) {
		Scanner scanner = new Scanner(inputStream);
		printStream.print("Departure Date and Time (yyyy-MM-dd HH:mm): ");
		String departureDate = scanner.nextLine();
		LocalDateTime dDate = Formatter.parse(departureDate);
		LocalDateTime aDate = Flight.calculateArrivalTime(dDate, Main.upcomingFlights.get(index).getFlightHours(), Main.upcomingFlights.get(index).getFlightMinutes());
		Main.upcomingFlights.get(index).setDepartureDate(dDate);
		Main.upcomingFlights.get(index).setArrivalDate(aDate);
	}

	private static void modifyDestination(int index) {
		Scanner scanner = new Scanner(inputStream);
		printStream.print("Destination:");
		String destination = scanner.nextLine();
		printStream.print("Flight length hours:");
		int hours = scanner.nextInt();
		printStream.print("minutes:");
		int minutes = scanner.nextInt();
		scanner.nextLine();
		LocalDateTime aDate = Flight.calculateArrivalTime(Main.upcomingFlights.get(index).getDepartureDate(), hours, minutes);
		Main.upcomingFlights.get(index).setDestination(destination);
		Main.upcomingFlights.get(index).setFlightHours(hours);
		Main.upcomingFlights.get(index).setFlightMinutes(minutes);
		Main.upcomingFlights.get(index).setArrivalDate(aDate);
				
	}

	private static void modifyDeparturePlace(int index) {
		Scanner scanner = new Scanner(inputStream);
		printStream.print("Departing from:");
		String departure = scanner.nextLine();
		printStream.print("Flight length hours:");
		int hours = scanner.nextInt();
		printStream.print("minutes:");
		int minutes = scanner.nextInt();
		scanner.nextLine();
		LocalDateTime aDate = Flight.calculateArrivalTime(Main.upcomingFlights.get(index).getDepartureDate(), hours, minutes);
		Main.upcomingFlights.get(index).setDeparture(departure);
		Main.upcomingFlights.get(index).setFlightHours(hours);
		Main.upcomingFlights.get(index).setFlightMinutes(minutes);
		Main.upcomingFlights.get(index).setArrivalDate(aDate);
		
	}

	public static int findFlight(int flightNumber) {
		for (int i = 0; i < Main.upcomingFlights.size(); i++) {
			if (Main.upcomingFlights.get(i).getFlightNumber() == flightNumber) {
				return i;
			}
		}
		return -1;
	}
	

	


}
