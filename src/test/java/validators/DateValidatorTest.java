package validators;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import metho_project.StubSystemClock;
import validators.DateValidator;

public class DateValidatorTest {

	public DateValidator dValidator;

	@Before
	public void setup() {
		dValidator = new DateValidator(new StubSystemClock(LocalDateTime.of(2010, 10, 10, 10, 10)));
	}

	@Test
	public void testValidateWhenValueIsEmpty() {
		assertEquals("Date is required", dValidator.validate(""));
	}

	@Test
	public void testValidateWhenValueIsAnInvalidFormat() {
		assertEquals("Invalid format", dValidator.validate("2020-12-12-10-10"));
	}

	@Test
	public void testValidateWhenValueIsAfterToday() {
		assertEquals(null, dValidator.validate("2020-10-10 10:10"));
	}

	@Test
	public void testValidateWhenValueIsLaterToday() {
		assertEquals(null, dValidator.validate("2010-10-10 10:20"));
	}

	@Test
	public void testValidateWhenValueIsEarlierToday() {
		assertEquals("Invalid date", dValidator.validate("2010-10-10 05:20"));
	}

	@Test
	public void testValidateWhenValueIsBeforeTodaysDate() {
		assertEquals("Invalid date", dValidator.validate("2009-12-12 10:10"));
	}

}
