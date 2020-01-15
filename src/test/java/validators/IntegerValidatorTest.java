package validators;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import validators.IntegerValidator;

public class IntegerValidatorTest {

	public IntegerValidator iValidator;

	@Before
	public void setup() {
		iValidator = new IntegerValidator(0, 100);
	}

	@Test
	public void testValidateWithNumberTooLow() {
		assertEquals("Please enter an integer value between 0 and 100 inclusive", iValidator.validate("-4"));
	}

	@Test
	public void testValidateWithNumberTooHigh() {
		assertEquals("Please enter an integer value between 0 and 100 inclusive", iValidator.validate("1000"));
	}

	@Test
	public void testValidateWithNonNumericValue() {
		assertEquals("Please enter an integer value", iValidator.validate("One"));
	}

	@Test
	public void testValidateWithMinimum() {
		assertEquals(null, iValidator.validate("0"));
	}

	@Test
	public void testValidateWithMaximum() {
		assertEquals(null, iValidator.validate("100"));
	}

	@Test
	public void testValidateWithValidNumberInTheMiddle() {
		assertEquals(null, iValidator.validate("50"));
	}

}
