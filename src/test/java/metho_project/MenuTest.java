package metho_project;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;

public class MenuTest {

	@Test
	public void testThatWhenUserEntersOneMethodReturnsOne() {
		Menu menu = new Menu();
		InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
		menu.setInputStream(inputStream);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		menu.setPrintStream(printStream);

		int item = menu.chooseItem();

		assertEquals(1, item);
		// too strict on the insurtions
		assertEquals("1. Item 1\r\n" + "2. Item 2\r\n" + "Please enter 1  or 2", outputStream.toString());
	}

}
