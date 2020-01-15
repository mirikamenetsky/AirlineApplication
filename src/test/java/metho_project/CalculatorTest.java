package metho_project;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testCalculateArrivalTimePositiveNumbers() {
		Calculator calc = new Calculator();
		LocalDateTime dDate = LocalDateTime.of(2020, 12, 12, 12, 12);
		int hours = 1;
		int minutes = 2;
		LocalDateTime expected = LocalDateTime.of(2020, 12, 12, 13, 14);
		assertEquals(expected, calc.calculateArrivalTime(dDate, hours, minutes));
	}

	@Test
	public void testCalculateArrivalTimeNegativeNumbers() {
		Calculator calc = new Calculator();
		LocalDateTime dDate = LocalDateTime.of(2020, 12, 12, 12, 12);
		int hours = -1;
		int minutes = -2;
		LocalDateTime expected = LocalDateTime.of(2020, 12, 12, 11, 10);
		assertEquals(expected, calc.calculateArrivalTime(dDate, hours, minutes));
	}

	@Test
	public void testCalculateArrivalTimeZero() {
		Calculator calc = new Calculator();
		LocalDateTime dDate = LocalDateTime.of(2020, 12, 12, 12, 12);
		int hours = 0;
		int minutes = 0;
		LocalDateTime expected = LocalDateTime.of(2020, 12, 12, 12, 12);
		assertEquals(expected, calc.calculateArrivalTime(dDate, hours, minutes));
	}

	@Test
	public void testCalculateArrivalTimeNewDay() {
		Calculator calc = new Calculator();
		LocalDateTime dDate = LocalDateTime.of(2020, 12, 12, 20, 12);
		int hours = 5;
		int minutes = 2;
		LocalDateTime expected = LocalDateTime.of(2020, 12, 13, 01, 14);
		assertEquals(expected, calc.calculateArrivalTime(dDate, hours, minutes));
	}

}
