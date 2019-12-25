package metho_project;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;

public class MenuTest {

	@Test
	public void testThatWhenUserEntersOneOneIsChosen() {
		Menu menu = new Menu();
		InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
		menu.setInputStream(inputStream);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		menu.setPrintStream(printStream);

		int item = menu.chooseItemMain();

		assertEquals(1, item);
		assertEquals("Please select one of the following options\r\n" + 
				"1. Add Flights\r\n" + 
				"2. View Available Flights\r\n" + 
				"3. Cancel Flight\r\n" + 
				"4. Modify Flight Details\r\n" + 
				"5. Advanced Flight Search\r\n" + 
				"6. Display Upcoming Flights\r\n" + 
				"7. Exit\r\n", outputStream.toString());
	}
	
	@Test
	public void testThatWhenUserEntersSevenSevenIsChosen() {
		Menu menu = new Menu();
		InputStream inputStream = new ByteArrayInputStream("7\n".getBytes());
		menu.setInputStream(inputStream);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);

		menu.setPrintStream(printStream);

		int item = menu.chooseItemMain();

		assertEquals(7, item);
		assertEquals("Please select one of the following options\r\n" + 
				"1. Add Flights\r\n" + 
				"2. View Available Flights\r\n" + 
				"3. Cancel Flight\r\n" + 
				"4. Modify Flight Details\r\n" + 
				"5. Advanced Flight Search\r\n" + 
				"6. Display Upcoming Flights\r\n" + 
				"7. Exit\r\n", outputStream.toString());
	}

}
