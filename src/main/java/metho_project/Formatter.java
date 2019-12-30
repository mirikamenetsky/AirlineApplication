package metho_project;

public class Formatter {

	public String flightDisplayer(Flight flight) {
		return "Flight Number: " + flight.getFlightNumber() + "\t\tDeparting from: " + flight.getDeparture() + "\t\t\t" + flight.getDepartureDate() + "\t\tFlight Length: "
				+ flight.getFlightHours() + " Hours " + 
				+ flight.getFlightMinutes() +  " Minutes"
				+ "\n\t\t\t\tArriving to: "
				+ flight.getDestination() + "\t\t\t" + flight.getArrivalDate() + "\t\tMaximum Passengers: "
				+ flight.getMaxPass() + "\n";
	}
}
