package metho_project;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import io.Prompter;
import validators.DateValidator;
import validators.StringValidator;

public class FlightEntryFormTest {

	public Prompter prompter;
	public StringValidator sValidator;
	public DateValidator dValidator;
	public Calculator calc;
	public FlightEntryForm fef;

	@Before
	public void setup() {
		prompter = mock(Prompter.class);
		sValidator = mock(StringValidator.class);
		dValidator = mock(DateValidator.class);
		calc = new Calculator();
	}

	@Test
	public void testCreateFlight() {
		when(prompter.prompt("\nDeparting from:", sValidator)).thenReturn("JFK");
		when(prompter.prompt("Destination:", sValidator)).thenReturn("LAX");
		when(prompter.prompt("Flight length hours:", 0, 21)).thenReturn(1);
		when(prompter.prompt("Flight length minutes:", 0, 59)).thenReturn(10);
		when(prompter.prompt("Maximum Passengers", 0, 544)).thenReturn(100);
		when(prompter.promptDate("Departure Date and Time (yyyy-MM-dd HH:mm): ", dValidator))
				.thenReturn(LocalDateTime.of(2020, 10, 10, 10, 10));

		FlightEntryForm fef = new FlightEntryForm(prompter, sValidator, dValidator, calc);
		Flight flight = fef.createFlight();

		assertEquals("JFK", flight.getDeparture());
		assertEquals("LAX", flight.getDestination());
		assertEquals(1, flight.getFlightHours());
		assertEquals(10, flight.getFlightMinutes());
		assertEquals(100, flight.getMaxPass());
		assertEquals(LocalDateTime.of(2020, 10, 10, 10, 10), flight.getDepartureDate());
		assertEquals(LocalDateTime.of(2020, 10, 10, 11, 20), flight.getArrivalDate());
	}
}
