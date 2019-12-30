package metho_project;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.time.LocalDateTime;

import org.junit.Test;

public class FlightModificationTest {

	@Test
	public void testAddFlights() throws ParseException{
		StringPrompter sPrompter = mock(StringPrompter.class);
		StringValidator sValidator = mock(StringValidator.class);
		when(sPrompter.promptUser(eq("Departing from:"),sValidator)).thenReturn("JFK");
		when(sPrompter.promptUser(eq("Destination:"),sValidator)).thenReturn("LAX");
	
		IntPrompter iPrompter = mock(IntPrompter.class);
		IntValidator iValidator = mock(IntValidator.class);
		when(iPrompter.promptUser(eq("Flight length hours:"),iValidator)).thenReturn(1);
		when(iPrompter.promptUser(eq("Flight length minutes:"), iValidator)).thenReturn(10);
		when(iPrompter.promptUser(eq("Maximum Passengers"), iValidator)).thenReturn(100);
		
		DatePrompter dPrompter = mock(DatePrompter.class);
		DateValidator dValidator = mock(DateValidator.class);
		LocalDateTime date = LocalDateTime.of(2020,10,10,10,10,00);
		when(dPrompter.promptUser(eq("Departure Date and Time (yyyy-MM-dd HH:mm): "), dValidator)).thenReturn(date);
		Calculator calc = new Calculator();
		//LocalDateTime arrivalDate = calc.calculateArrivalTime(date, 1, 10);
		
		FlightModification fm = new FlightModification(sPrompter, sValidator, dPrompter, dValidator, iPrompter, iValidator, 
				new Calculator(), new Search(sPrompter, new StubSystemClock(LocalDateTime.of(2020,11,20,11,20,00)), 
						System.out, new Formatter(), sValidator, dPrompter, dValidator));
	
		fm.addFlights();


		assertEquals("JFK", Main.upcomingFlights.get(0).getDeparture());
		assertEquals("LAX", Main.upcomingFlights.get(0).getDestination());
		assertEquals(1, Main.upcomingFlights.get(0).getFlightHours());
		assertEquals(10, Main.upcomingFlights.get(0).getFlightMinutes());
		assertEquals(100, Main.upcomingFlights.get(0).getMaxPass());
		assertEquals("2020-10-10T10:10:00", Main.upcomingFlights.get(0).getDepartureDate());
		assertEquals("2020-10-10T11:20:00", Main.upcomingFlights.get(0).getArrivalDate());
	}

}
