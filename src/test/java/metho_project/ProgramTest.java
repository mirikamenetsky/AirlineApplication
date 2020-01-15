package metho_project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import io.Prompter;
import validators.DateValidator;
import validators.RepeatValidator;
import validators.StringValidator;

public class ProgramTest {
	public Menu menu;
	public Prompter prompter;
	public RepeatValidator rv;
	public StringValidator sv;
	public DateValidator dv;
	public Searcher searcher;
	public FlightModification fm;
	public Formatter formatter;
	public List<Flight> flights;
	public FlightEntryForm fef;
	public Calculator calc;
	public Program program;

	@Before
	public void setup() {
		prompter = mock(Prompter.class);
		menu = new Menu(prompter);
		formatter = new Formatter();
		rv = mock(RepeatValidator.class);
		sv = mock(StringValidator.class);
		dv = mock(DateValidator.class);
		flights = new ArrayList<>();
		createFlights();
		searcher = new Searcher(prompter, new StubSystemClock(LocalDateTime.of(2020, 11, 20, 11, 20)), formatter, sv,
				dv, flights);
		calc = new Calculator();
		fm = new FlightModification(prompter, sv, dv, calc, searcher, flights);
		fef = new FlightEntryForm(prompter, sv, dv, calc);
		program = new Program(menu, searcher, fm, prompter, rv, fef, flights);

	}

	private void createFlights() {
		flights.add(new Flight("FLL", "JFK", 100, LocalDateTime.of(2020, 12, 10, 10, 10), 1, 5,
				LocalDateTime.of(2020, 12, 10, 11, 15), 1));
		flights.add(new Flight("JFK", "FLL", 100, LocalDateTime.of(2020, 11, 21, 10, 10), 1, 5,
				LocalDateTime.of(2020, 11, 21, 11, 15), 2));
		flights.add(new Flight("LaGuardia", "Newirk", 100, LocalDateTime.of(2020, 10, 10, 10, 10), 1, 5,
				LocalDateTime.of(2020, 10, 10, 11, 15), 3));
	}

	@Test
	public void testExecuteOperationThatWillThrowException() {
		try {
			program.executeOperation(8);
			fail();
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testAddFlightsWithOneFlight() {
		when(prompter.prompt("\nDeparting from:", sv)).thenReturn("JFK");
		when(prompter.prompt("Destination:", sv)).thenReturn("LAX");
		when(prompter.prompt("Flight length hours:", 0, 21)).thenReturn(1);
		when(prompter.prompt("Flight length minutes:", 0, 59)).thenReturn(10);
		when(prompter.prompt("Maximum Passengers", 0, 544)).thenReturn(100);
		when(prompter.promptDate("Departure Date and Time (yyyy-MM-dd HH:mm): ", dv))
				.thenReturn(LocalDateTime.of(2020, 12, 10, 10, 10));
		when(prompter.prompt("Would you like to schedule another flight? Y or N", rv)).thenReturn("N");
		program.executeOperation(1);
		assertEquals("JFK", flights.get(flights.size() - 1).getDeparture());
		assertEquals("LAX", flights.get(flights.size() - 1).getDestination());
		assertEquals(LocalDateTime.of(2020, 12, 10, 10, 10), flights.get(flights.size() - 1).getDepartureDate());
	}

	@Test
	public void testAddFlightsWithTwoFlight() {
		when(prompter.prompt("Would you like to schedule another flight? Y or N", rv)).thenReturn("Yes", "No");
		when(prompter.prompt("\nDeparting from:", sv)).thenReturn("EWR", "FLL");
		when(prompter.prompt("Destination:", sv)).thenReturn("FLL", "EWR");
		when(prompter.prompt("Flight length hours:", 0, 21)).thenReturn(3, 3);
		when(prompter.prompt("Flight length minutes:", 0, 59)).thenReturn(3, 20);
		when(prompter.prompt("Maximum Passengers", 0, 544)).thenReturn(100, 100);
		when(prompter.promptDate("Departure Date and Time (yyyy-MM-dd HH:mm): ", dv))
				.thenReturn(LocalDateTime.of(2020, 12, 10, 12, 26), LocalDateTime.of(2020, 12, 01, 10, 10));

		program.executeOperation(1);

		assertEquals(4, flights.get(flights.size() - 2).getFlightNumber());
		assertEquals("EWR", flights.get(flights.size() - 2).getDeparture());
		assertEquals("FLL", flights.get(flights.size() - 2).getDestination());
		assertEquals(3, flights.get(flights.size() - 2).getFlightHours());
		assertEquals(3, flights.get(flights.size() - 2).getFlightMinutes());
		assertEquals(100, flights.get(flights.size() - 2).getMaxPass());
		assertEquals(LocalDateTime.of(2020, 12, 10, 12, 26), flights.get(flights.size() - 2).getDepartureDate());

		assertEquals(5, flights.get(flights.size() - 1).getFlightNumber());
		assertEquals("FLL", flights.get(flights.size() - 1).getDeparture());
		assertEquals("EWR", flights.get(flights.size() - 1).getDestination());
		assertEquals(3, flights.get(flights.size() - 1).getFlightHours());
		assertEquals(20, flights.get(flights.size() - 1).getFlightMinutes());
		assertEquals(100, flights.get(flights.size() - 1).getMaxPass());
		assertEquals(LocalDateTime.of(2020, 12, 01, 10, 10), flights.get(flights.size() - 1).getDepartureDate());
	}

	@Test
	public void testCancelFlightSuccessful() {
		when(prompter.promptFlight("Enter Flight Number:")).thenReturn(1);
		program.executeOperation(3);
		verify(prompter).println("Flight removed successfully");
	}

	@Test
	public void testCancelFlightUnsuccessful() {
		when(prompter.promptFlight("Enter Flight Number:")).thenReturn(-7);
		program.executeOperation(3);
		verify(prompter).println("Flight not Found");
	}

	@Test
	public void testUpcomingFlightsWithinOneWeek() {
		program.executeOperation(6);
		verify(prompter).println("\n---Available Flights---");
		verify(prompter).println(
				"Flight Number: 2\t\tDeparting from: JFK\t\t\t2020-11-21T10:10\t\tFlight Length: 1 Hours 5 Minutes\n\t\t\t\t"
						+ "Arriving to: FLL\t\t\t2020-11-21T11:15\t\tMaximum Passengers: 100\n");
	}

	@Test
	public void testModifyFlightDepartureSuccessful() {
		when(prompter.promptFlight("Enter Flight Number:")).thenReturn(1);
		when(prompter.prompt("Please select option 1 or 2", 1, 2)).thenReturn(1);
		program.executeOperation(4);
		verify(prompter).println("Flight modified successfully");
	}

	@Test
	public void testModifyFlightDepartureUnsuccessful() {
		program.executeOperation(4);
		when(prompter.promptFlight("Enter Flight Number:")).thenReturn(-5);
		verify(prompter).println("Flight does not exist");

	}

	@Test
	public void testModifyFlightDestinationSuccessful() {
		when(prompter.promptFlight("Enter Flight Number:")).thenReturn(1);
		when(prompter.prompt("Please select option 1 or 2", 1, 2)).thenReturn(2);
		program.executeOperation(4);
		verify(prompter).println("Flight modified successfully");
	}

	@Test
	public void testModifyFlightDestinationUnsuccessful() {
		when(prompter.promptFlight("Enter Flight Number:")).thenReturn(-5);
		program.executeOperation(4);
		verify(prompter).println("Flight does not exist");
	}

	@Test
	public void testSearchForFlightsThatWillThrowException() {
		try {
			when(prompter.prompt("Please select option 1 or 2", 1, 2)).thenReturn(4);
			program.executeOperation(5);
			fail();
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testSearchForFlightsByDeparture() {
		when(prompter.prompt("Please select option 1 or 2", 1, 2)).thenReturn(1);
		when(prompter.promptDate("Enter date to view available flights (yyyy-MM-dd 00:00):", dv))
				.thenReturn(LocalDateTime.of(2020, 12, 10, 0, 0));
		when(prompter.prompt("Enter Departure Location", sv)).thenReturn("FLL");
		program.executeOperation(5);
		verify(prompter).println("\n---Available Flights---");
		verify(prompter).println(
				"Flight Number: 1\t\tDeparting from: FLL\t\t\t2020-12-10T10:10\t\tFlight Length: 1 Hours 5 Minutes\n\t\t\t\t"
						+ "Arriving to: JFK\t\t\t2020-12-10T11:15\t\tMaximum Passengers: 100\n");
	}

	@Test
	public void testSearchForFlightsByDepartureWithNoFlightsOnThatDateButExistingDeparturePlace() {
		when(prompter.prompt("Please select option 1 or 2", 1, 2)).thenReturn(1);
		when(prompter.promptDate("Enter date to view available flights (yyyy-MM-dd 00:00):", dv))
				.thenReturn(LocalDateTime.of(2020, 12, 26, 0, 0));
		when(prompter.prompt("Enter Departure Location", sv)).thenReturn("JFK");
		program.executeOperation(5);
		verify(prompter).println("\n---No flights to display---");
	}

	@Test
	public void testSearchForFlightsByDepartureWithFlightsOnThatDateButNoneDepartingFromPlace() {
		when(prompter.prompt("Please select option 1 or 2", 1, 2)).thenReturn(1);
		when(prompter.promptDate("Enter date to view available flights (yyyy-MM-dd 00:00):", dv))
				.thenReturn(LocalDateTime.of(2020, 12, 10, 0, 0));
		when(prompter.prompt("Enter Departure Location", sv)).thenReturn("Miami");
		program.executeOperation(5);
		verify(prompter).println("\n---No flights to display---");
	}

	@Test
	public void testSearchForFlightsByDestination() {
		when(prompter.prompt("Please select option 1 or 2", 1, 2)).thenReturn(2);
		when(prompter.promptDate("Enter date to view available flights (yyyy-MM-dd 00:00):", dv))
				.thenReturn(LocalDateTime.of(2020, 12, 10, 0, 0));
		when(prompter.prompt("Enter Destination", sv)).thenReturn("JFK");
		program.executeOperation(5);
		verify(prompter).println("\n---Available Flights---");
		verify(prompter).println(
				"Flight Number: 1\t\tDeparting from: FLL\t\t\t2020-12-10T10:10\t\tFlight Length: 1 Hours 5 Minutes\n\t\t\t\t"
						+ "Arriving to: JFK\t\t\t2020-12-10T11:15\t\tMaximum Passengers: 100\n");
	}

	@Test
	public void testSearchForFlightsByDestinationWithNoFlightsOnThatDateButExistingDestination() {
		when(prompter.prompt("Please select option 1 or 2", 1, 2)).thenReturn(2);
		when(prompter.promptDate("Enter date to view available flights (yyyy-MM-dd 00:00):", dv))
				.thenReturn(LocalDateTime.of(2020, 12, 25, 0, 0));
		when(prompter.prompt("Enter Destination", sv)).thenReturn("JFK");
		program.executeOperation(5);
		verify(prompter).println("\n---No flights to display---");
	}

	@Test
	public void testSearchForFlightsByDestinationWithFlightsOnThatDateButNoneToDestination() {
		when(prompter.prompt("Please select option 1 or 2", 1, 2)).thenReturn(2);
		when(prompter.promptDate("Enter date to view available flights (yyyy-MM-dd 00:00):", dv))
				.thenReturn(LocalDateTime.of(2020, 12, 10, 0, 0));
		when(prompter.prompt("Enter Destination", sv)).thenReturn("Miami");
		program.executeOperation(5);
		verify(prompter).println("\n---No flights to display---");
	}

	@Test
	public void testViewFlightsWithOneFlightNotShowingBecauseDatePassed() {
		program.executeOperation(2);
		verify(prompter).println("\n---Available Flights---");
		verify(prompter).println(
				"Flight Number: 1\t\tDeparting from: FLL\t\t\t2020-12-10T10:10\t\tFlight Length: 1 Hours 5 Minutes\n\t\t\t\t"
						+ "Arriving to: JFK\t\t\t2020-12-10T11:15\t\tMaximum Passengers: 100\n");
		verify(prompter).println(
				"Flight Number: 2\t\tDeparting from: JFK\t\t\t2020-11-21T10:10\t\tFlight Length: 1 Hours 5 Minutes\n\t\t\t\t"
						+ "Arriving to: FLL\t\t\t2020-11-21T11:15\t\tMaximum Passengers: 100\n");

	}
}
