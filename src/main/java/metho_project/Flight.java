package metho_project;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Flight {

	private String departure;
	private String destination;
	private int maxPass;
	private LocalDateTime departureDate;
	private LocalDateTime arrivalDate;
	private int flightLengthHours;
	private int flightLengthMinutes;
	private static int flights = 1;
	private int flightNumber;
	private static InputStream inputStream;
	private static PrintStream printStream;

	public Flight() {

	}

	public Flight(String departure, String destination, int maxPass, LocalDateTime departureDate, int flightLengthHours,
			int flightLengthMinutes, LocalDateTime arrivalDate) {
		this.departure = departure;
		this.destination = destination;
		this.maxPass = maxPass;
		this.departureDate = departureDate;
		this.flightLengthHours = flightLengthHours;
		this.flightLengthMinutes = flightLengthMinutes;
		this.arrivalDate = arrivalDate;
		this.flightNumber = flights;
		flights++;

	}

	public void setPrintStream(PrintStream printStream) {
		this.printStream = printStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getMaxPass() {
		return maxPass;
	}

	public void setMaxPass(int maxPass) {
		this.maxPass = maxPass;
	}

	public LocalDateTime getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public void setFlightHours(int hours) {
		this.flightLengthHours = hours;
	}

	public void setFlightMinutes(int minutes) {
		this.flightLengthMinutes = minutes;
	}

	public int getFlightHours() {
		return flightLengthHours;
	}

	public int getFlightMinutes() {
		return flightLengthMinutes;
	}

	public static void viewFlights(List<Flight> flights) {
		if (flights.isEmpty()) {
			printStream.println("---No flights to display---");
			return;
		}
		printStream.println("---Available Flights---");
		for (Flight temp : flights) {
			printStream.println(Formatter.flightDisplayer(temp));
		}
	}



	public static void executeFlightSearchChoice(int choice, LocalDate date) {
		switch (choice) {
		case 1:
			searchByDeparture(date);
			break;
		case 2:
			searchByDestination(date);
			break;
		default:
			printStream.println("That was not an available option");
		}
	}

	public static void searchByDestination(LocalDate date) {
		Scanner scanner = new Scanner(inputStream);
		List<Flight> flights = new ArrayList<>();
		printStream.println("Enter Destination");
		String destination = scanner.nextLine();
		for (Flight f : Main.upcomingFlights) {
			LocalDate departingDate = f.getDepartureDate().toLocalDate();
			if (departingDate.equals(date)) {
				if (destination.equalsIgnoreCase(f.getDestination())) {
					flights.add(f);
				}
			}
		}
		viewFlights(flights);

	}

	public static void searchByDeparture(LocalDate date) {
		Scanner scanner = new Scanner(inputStream);
		List<Flight> flights = new ArrayList<>();
		printStream.println("Enter Departure Location");
		String departure = scanner.nextLine();
		for (Flight f : Main.upcomingFlights) {
			LocalDate departingDate = f.getDepartureDate().toLocalDate();
			if (departingDate.equals(date)) {
				if (departure.equalsIgnoreCase(f.getDeparture())) {
					flights.add(f);
				}
			}
		}
		viewFlights(flights);

	}

	public static LocalDateTime calculateArrivalTime(LocalDateTime dDate, int hours, int minutes) {
		dDate = dDate.plusHours(hours).plusMinutes(minutes);
		return dDate;
	}

	public static void displayUpcomingFlights(SystemClock sysClock) {
		Calculator calc = new Calculator();
		calc.setSystemClock(sysClock);
		List<Flight> flights = calc.upcomingFlights();
		viewFlights(flights);

	}

}
