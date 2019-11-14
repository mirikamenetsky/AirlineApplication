package metho_project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Flight {

	private String departure;
	private String destination;
	private int maxPass;
	private Date departureDate;
	private Date arrivalDate;
	private double flightLength;

	public Flight(String departure, String destination, int maxPass, String departureDate, String flightLength)
			throws ParseException {
		this.departure = departure;
		this.destination = destination;
		this.maxPass = maxPass;
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		Date departureDt = (Date) formatter.parse(departureDate);
		this.departureDate = departureDt;
		calculateArrivalTime(flightLength);
		this.flightLength = Double.parseDouble(flightLength.replace(":","."));

	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output
			.append("Departing from: " + departure)
			.append("\t\t\t" + departureDate)
			.append("\t\tFlight Length: " + String.valueOf(flightLength).substring(0,2) + " Hours " + String.valueOf(flightLength).substring(3,5)+ " Min")
			.append("\nArriving to: " + destination)
			.append("\t\t\t" +arrivalDate)
			.append("\t\tMaximum Passengers: " + maxPass + "\n");
		return output.toString();
	}

	public void calculateArrivalTime(String flightLength) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(departureDate);
		int hour = Integer.parseInt(flightLength.substring(0, 2));
		int min = Integer.parseInt(flightLength.substring(3, 5));
		cal.add(Calendar.HOUR, hour);
		cal.add(Calendar.MINUTE, min);
		arrivalDate = cal.getTime();
	}

}
