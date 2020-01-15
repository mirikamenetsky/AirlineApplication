package validators;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import validators.RepeatValidator;

public class RepeatValidatorTest {

	public RepeatValidator rValidator;

	@Before
	public void setup() {
		rValidator = new RepeatValidator();
	}

	@Test
	public void testValidateWithLowerCaseY() {
		assertEquals(null, rValidator.validate("y"));
	}

	@Test
	public void testValidateWithLowerCaseN() {
		assertEquals(null, rValidator.validate("n"));
	}

	@Test
	public void testValidateWithUpperCaseY() {
		assertEquals(null, rValidator.validate("Y"));
	}

	@Test
	public void testValidateWithUpperCaseN() {
		assertEquals(null, rValidator.validate("N"));
	}

	@Test
	public void testValidateWithYes() {
		assertEquals(null, rValidator.validate("Yes"));
	}

	@Test
	public void testValidateWithNo() {
		assertEquals(null, rValidator.validate("No"));
	}

	@Test
	public void testWithRandomLetterOtherThanYOrN() {
		assertEquals("Enter Y or N", rValidator.validate("z"));
	}

	// not sure if we need it
	@Test
	public void testWithRandomWordOtherThanThoseThatStartWithYOrN() {
		assertEquals("Enter Y or N", rValidator.validate("zebra"));
	}

	@Test
	public void testWhenValueIsEmpty() {
		assertEquals("Enter Y or N", rValidator.validate(""));
	}
}
