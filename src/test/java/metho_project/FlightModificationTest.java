package metho_project;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.Prompter;
import validators.*;

public class FlightModificationTest {

	public Prompter prompter;
	public StringValidator sValidator;
	public DateValidator dValidator;
	public FlightModification fm;
	public List<Flight> flights;
	public Searcher searcher;
	public Calculator calc;

	@Before
	public void setup() {
		prompter = mock(Prompter.class);
		sValidator = mock(StringValidator.class);
		dValidator = mock(DateValidator.class);
		flights = new ArrayList<>();
		createFlight();
		searcher = new Searcher(prompter, new StubSystemClock(LocalDateTime.of(2020, 11, 20, 11, 20)), new Formatter(),
				sValidator, dValidator, flights);
		calc = new Calculator();
		fm = new FlightModification(prompter, sValidator, dValidator, calc, searcher, flights);

	}

	public void createFlight() {
		flights.add(new Flight("FLL", "JFK", 100, LocalDateTime.of(2020, 01, 10, 10, 10), 1, 5,
				LocalDateTime.of(2020, 01, 10, 11, 15), 1));
		flights.add(new Flight("JFK", "FLL", 100, LocalDateTime.of(2020, 01, 10, 10, 10), 1, 5,
				LocalDateTime.of(2020, 01, 10, 11, 15), 2));
		flights.add(new Flight("LaGuardia", "Newirk", 100, LocalDateTime.of(2020, 01, 10, 10, 10), 1, 5,
				LocalDateTime.of(2020, 01, 10, 11, 15), 3));
	}

	@Test
	public void testCancelFlightWithFlightNumberThatExists() {
		when(prompter.promptFlight("Enter Flight Number:")).thenReturn(1);
		assertTrue(fm.cancelFlight());

	}

	@Test
	public void testCancelFlightWithFlightNumberWithNegativeFlightNumber() {
		when(prompter.promptFlight("Enter Flight Number:")).thenReturn(-4);
		assertFalse(fm.cancelFlight());

	}

	@Test
	public void testCancelFlightWithFlightNumberThatDoesNotExist() {
		when(prompter.promptFlight("Enter Flight Number:")).thenReturn(4);
		assertFalse(fm.cancelFlight());

	}

	@Test
	public void testGetFlightNumFromUser() {
		when(prompter.promptFlight("Enter Flight Number:")).thenReturn(2);
		int expected = 2;
		assertEquals(expected, fm.getFlightNumFromUser());

	}

	@Test
	public void testExecuteModificationChoiceThatWillThrowException() {
		try {
			fm.executeModificationChoice(5, 0);
			fail();
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testModifyDepartureDateTime() {
		when(prompter.promptDate("Enter new Departure Date and Time (yyyy-MM-dd HH:mm): ", dValidator))
				.thenReturn(LocalDateTime.of(2021, 10, 10, 10, 10));
		LocalDateTime expectedDepartureTime = LocalDateTime.of(2021, 10, 10, 10, 10);
		LocalDateTime expectedArrivalTime = LocalDateTime.of(2021, 10, 10, 11, 15);
		fm.executeModificationChoice(3, 0);
		assertEquals(expectedDepartureTime, flights.get(0).getDepartureDate());
		assertEquals(expectedArrivalTime, flights.get(0).getArrivalDate());
	}

	@Test
	public void testModifyDestination() {
		when(prompter.prompt("Destination:", sValidator)).thenReturn("MIA");
		when(prompter.prompt("Flight length hours:", 0, 21)).thenReturn(2);
		when(prompter.prompt("minutes:", 0, 59)).thenReturn(15);
		String expectedDestination = "MIA";
		int expectedFlightHours = 2;
		int expectedFlightMin = 15;
		LocalDateTime expectedArrivalTime = LocalDateTime.of(2020, 01, 10, 12, 25);
		fm.executeModificationChoice(2, 1);
		assertEquals(expectedDestination, flights.get(1).getDestination());
		assertEquals(expectedFlightHours, flights.get(1).getFlightHours());
		assertEquals(expectedFlightMin, flights.get(1).getFlightMinutes());
		assertEquals(expectedArrivalTime, flights.get(1).getArrivalDate());
	}

	@Test
	public void testModifyDeparturePlace() {
		when(prompter.prompt("Departing from:", sValidator)).thenReturn("LAX");
		when(prompter.prompt("Flight length hours:", 0, 21)).thenReturn(6);
		when(prompter.prompt("minutes:", 0, 59)).thenReturn(30);
		String expectedDeparturePlace = "LAX";
		int expectedFlightHours = 6;
		int expectedFlightMin = 30;
		LocalDateTime expectedArrivalTime = LocalDateTime.of(2020, 01, 10, 16, 40);
		fm.executeModificationChoice(1, 2);
		assertEquals(expectedDeparturePlace, flights.get(2).getDeparture());
		assertEquals(expectedFlightHours, flights.get(2).getFlightHours());
		assertEquals(expectedFlightMin, flights.get(2).getFlightMinutes());
		assertEquals(expectedArrivalTime, flights.get(2).getArrivalDate());
	}

}
