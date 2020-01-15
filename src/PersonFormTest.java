package miriMocks;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.matches;
public class PersonFormTest {

	@Test
	public void testEnterPerson() {
		Prompter prompter = mock(Prompter.class);
		when(prompter.promptUser(matches(".*first name.*"))).thenReturn("Joe");
		when(prompter.promptUser("Enter last name:")).thenReturn("Smith");
		when(prompter.promptUser("Enter phone number:")).thenReturn("123");
	
		PersonForm form = new PersonForm(prompter);

		Person person = form.enterPerson();

		assertEquals("Joe", person.getFirstName());
		assertEquals("Smith", person.getLastName());
		assertEquals("123", person.getPhoneNumber());
	}

}
