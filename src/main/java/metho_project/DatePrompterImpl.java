package metho_project;

import java.time.LocalDateTime;
import java.util.Scanner;

public class DatePrompterImpl implements DatePrompter{
	
	private final Scanner scanner = new Scanner(System.in);

	public LocalDateTime promptUser(String prompt, DateValidator validator) {
		while (true) {
			LocalDateTime value = promptOnce(prompt, validator);
			if (value != null) {
				return value;
			}
		}
	}

	public LocalDateTime promptOnce(String prompt, DateValidator validator) {
		System.out.println(prompt);
		String value = scanner.nextLine();
		String message = validator.validate(value) == null ? null : validator.validate(value);
		if (message != null) {
			System.out.println(message);
		}
		return message == null ? validator.getDate() : null;
	}

}
