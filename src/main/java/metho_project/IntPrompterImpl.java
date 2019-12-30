package metho_project;

import java.util.Scanner;

public class IntPrompterImpl implements IntPrompter {

	private final Scanner scanner = new Scanner(System.in);

	public int promptOnce(String prompt, IntValidator validator) {
		System.out.println(prompt);
		int value = scanner.nextInt();
		String message = validator.validate(value) == null ? null : validator.validate(value);
		if (message != null) {
			System.out.println(message);
		}
		return value >= 0 ? value : -1;
	}

	public int promptUser(String prompt, IntValidator validator) {
		while (true) {
			int value = promptOnce(prompt, validator);
			if (value != -1) {
				return value;
			}
		}
	}

}
