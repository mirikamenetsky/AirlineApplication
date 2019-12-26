package metho_project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {

	public static String flightDisplayer(Flight flight) {
		return "Flight Number: " + flight.getFlightNumber() + "\t\tDeparting from: " + flight.getDeparture() + "\t\t\t" + flight.getDepartureDate() + "\t\tFlight Length: "
				+ flight.getFlightHours() + " Hours " + 
				+ flight.getFlightMinutes() +  " Minutes"
				+ "\n\t\t\t\tArriving to: "
				+ flight.getDestination() + "\t\t\t" + flight.getArrivalDate() + "\t\tMaximum Passengers: "
				+ flight.getMaxPass() + "\n";
	}
	
	public static LocalDateTime parse(String departureDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime departureDt = LocalDateTime.parse(departureDate, formatter);
		return departureDt;
	}
}
