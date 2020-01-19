package metho_project;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testCalculateArrivalTimePositiveNumbers() {
		LocalDateTime dDate = LocalDateTime.of(2020, 12, 12, 12, 12);
		int hours = 1;
		int minutes = 2;
		LocalDateTime expected = LocalDateTime.of(2020, 12, 12, 13, 14);
		assertEquals(expected, Calculator.calculateArrivalTime(dDate, hours, minutes));
	}

	@Test
	public void testCalculateArrivalTimeNegativeNumbers() {
		LocalDateTime dDate = LocalDateTime.of(2020, 12, 12, 12, 12);
		int hours = -1;
		int minutes = -2;
		LocalDateTime expected = LocalDateTime.of(2020, 12, 12, 11, 10);
		assertEquals(expected, Calculator.calculateArrivalTime(dDate, hours, minutes));
	}

	@Test
	public void testCalculateArrivalTimeZero() {
		LocalDateTime dDate = LocalDateTime.of(2020, 12, 12, 12, 12);
		int hours = 0;
		int minutes = 0;
		LocalDateTime expected = LocalDateTime.of(2020, 12, 12, 12, 12);
		assertEquals(expected, Calculator.calculateArrivalTime(dDate, hours, minutes));
	}

	@Test
	public void testCalculateArrivalTimeNewDay() {
		LocalDateTime dDate = LocalDateTime.of(2020, 12, 12, 20, 12);
		int hours = 5;
		int minutes = 2;
		LocalDateTime expected = LocalDateTime.of(2020, 12, 13, 01, 14);
		assertEquals(expected, Calculator.calculateArrivalTime(dDate, hours, minutes));
	}

}
