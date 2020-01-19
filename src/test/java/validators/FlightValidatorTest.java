package validators;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import data.Flight;
import validators.FlightValidator;

public class FlightValidatorTest {

	public List<Flight> flights;
	public FlightValidator fValidator;

	@Before
	public void setup() {
		flights = new ArrayList<>();
		flights.add(new Flight("FLL", "JFK", 100, LocalDateTime.of(2020, 01, 10, 10, 10), 1, 5,
				LocalDateTime.of(2020, 01, 10, 11, 15), 1));
		flights.add(new Flight("JFK", "FLL", 100, LocalDateTime.of(2020, 01, 10, 10, 10), 1, 5,
				LocalDateTime.of(2020, 01, 10, 11, 15), 2));
		fValidator = new FlightValidator();

	}

	@Test
	public void testWithNegativeNumber() {
		assertEquals("Invalid flight number", fValidator.validate("-5"));
	}

	@Test
	public void testWithNonNumber() {
		assertEquals("Please enter an integer value", fValidator.validate("One"));
	}

	@Test
	public void testWithValidFlightNumber() {
		assertEquals(null, fValidator.validate("2"));
	}
}
