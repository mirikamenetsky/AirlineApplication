package validators;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import validators.StringValidator;

public class StringValidatorTest {

	public StringValidator sValidator;

	@Before
	public void setup() {
		sValidator = new StringValidator();
	}

	@Test
	public void testValidateWithValue() {
		assertEquals(null, sValidator.validate("Yayyyy, it worked:)"));
	}

	@Test
	public void testValidateWithEmptyValue() {
		assertEquals("input is required", sValidator.validate(""));
	}
}
