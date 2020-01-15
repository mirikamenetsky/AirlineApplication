package metho_project;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import io.Prompter;

public class MenuTest {

	public Prompter prompter;
	public Menu menu;

	@Before
	public void setup() {
		prompter = Mockito.mock(Prompter.class);
		menu = new Menu(prompter);
	}

	@Test
	public void testChooseItemMain4() {
		when(prompter.prompt("Please select from options 1-7", 1, 7)).thenReturn(4);
		assertEquals(4, menu.chooseItemMain());
	}

	@Test
	public void testChooseItemToModify2() {
		when(prompter.prompt("Please select option 1 or 2", 1, 2)).thenReturn(2);
		assertEquals(2, menu.chooseItemToModify());
	}

	@Test
	public void testflightSearch1() {
		when(prompter.prompt("Please select option 1 or 2", 1, 2)).thenReturn(1);
		assertEquals(1, menu.chooseFlightsToSearch());
	}
}
