package metho_project;

import java.util.Scanner;

public class StringPrompterImpl implements StringPrompter{
	
	private final Scanner scanner = new Scanner(System.in);

	public String promptUser(String prompt, StringValidator validator) {
		while (true) {
			String value = promptOnce(prompt, validator);
			if (value != null) {
				return value;
			}
		}
	}

	public String promptOnce(String prompt, StringValidator validator) {
		System.out.println(prompt);
		String value = scanner.nextLine();
		String message = validator.validate(value) == null ? null : validator.validate(value);
		if (message != null) {
			System.out.println(message);
		}
		return message == null ? value : null;
	}
}
