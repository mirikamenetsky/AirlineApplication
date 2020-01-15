package metho_project;

import java.time.LocalDateTime;

public class Flight {

	private String departure;
	private String destination;
	private int maxPass;
	private LocalDateTime departureDate;
	private LocalDateTime arrivalDate;
	private int flightLengthHours;
	private int flightLengthMinutes;
	private int flightNumber;
	private static int flights = 1;

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

	public Flight(String departure, String destination, int maxPass, LocalDateTime departureDate, int flightLengthHours,
			int flightLengthMinutes, LocalDateTime arrivalDate, int flightNumber) {
		this.departure = departure;
		this.destination = destination;
		this.maxPass = maxPass;
		this.departureDate = departureDate;
		this.flightLengthHours = flightLengthHours;
		this.flightLengthMinutes = flightLengthMinutes;
		this.arrivalDate = arrivalDate;
		this.flightNumber = flightNumber;
		flights = ++flightNumber;
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

}
