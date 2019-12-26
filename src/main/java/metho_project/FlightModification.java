package metho_project;

import java.io.InputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.time.LocalDateTime;
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
		Scanner scanner = new Scanner(inputStream);
		char again = 'Y';

		while (again == 'Y') {

			printStream.print("Departing from:");
			String departure = scanner.nextLine();

			printStream.print("Departure Date and Time (yyyy-MM-dd HH:mm): ");
			String departureDate = scanner.nextLine();

			printStream.print("Destination:");
			String destination = scanner.nextLine();

			printStream.print("Flight length hours:");
			int flightLengthHours = scanner.nextInt();

			printStream.print("Flight length minutes:");
			int flightLengthMin = scanner.nextInt();

			printStream.print("Maximum Passengers");
			int maxPass = scanner.nextInt();

			scanner.nextLine();

			LocalDateTime departDate = Formatter.parse(departureDate);
			LocalDateTime arriveDate = Flight.calculateArrivalTime(departDate, flightLengthHours, flightLengthMin);
			
			Main.upcomingFlights.add(new Flight(departure, destination, maxPass, departDate, flightLengthHours,
					flightLengthMin, arriveDate));
			System.out.println("Would you like to schedule another flight? Y or N");
			String input = scanner.nextLine().toUpperCase();
			again = input.charAt(0);
		}
	}

	public static boolean cancelFlight() {
		int flightNumber = getFlightNumFromUser();
		int removeIndex = Search.findFlight(flightNumber);
		if (removeIndex >= 0) {
			Main.upcomingFlights.remove(removeIndex);
			return true;
		}
		return false;
	}

	public static int getFlightNumFromUser() {
		Scanner scanner = new Scanner(inputStream);
		printStream.print("Enter Flight Number:");
		int flightNum = scanner.nextInt();
		scanner.nextLine();
		return flightNum;
	}

	public static void executeModificationChoice(int choice, int index) {
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

	private static void modifyDepartureDateTime(int index) {
		Scanner scanner = new Scanner(inputStream);
		printStream.print("Enter new Departure Date and Time (yyyy-MM-dd HH:mm): ");
		String departureDate = scanner.nextLine();
		LocalDateTime dDate = Formatter.parse(departureDate);
		LocalDateTime aDate = Flight.calculateArrivalTime(dDate, Main.upcomingFlights.get(index).getFlightHours(),
				Main.upcomingFlights.get(index).getFlightMinutes());
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
		LocalDateTime aDate = Flight.calculateArrivalTime(Main.upcomingFlights.get(index).getDepartureDate(), hours,
				minutes);
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
		LocalDateTime aDate = Flight.calculateArrivalTime(Main.upcomingFlights.get(index).getDepartureDate(), hours,
				minutes);
		Main.upcomingFlights.get(index).setDeparture(departure);
		Main.upcomingFlights.get(index).setFlightHours(hours);
		Main.upcomingFlights.get(index).setFlightMinutes(minutes);
		Main.upcomingFlights.get(index).setArrivalDate(aDate);

	}
}
