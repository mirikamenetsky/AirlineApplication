package io;

import java.time.LocalDateTime;
import validators.*;

public class PrompterImpl implements Prompter {

	private final InputOutput io;

	public PrompterImpl(InputOutput io) {
		this.io = io;
	}

	@Override
	public String prompt(String message) {
		print(message);
		return io.readLine();
	}

	@Override
	public void print(String message) {
		io.print(message);
	}

	@Override
	public void println(String message) {
		print(String.format("%s%n", message));
	}

	@Override
	public int prompt(String message, int minimum, int maximum) {
		String str = prompt(message, new IntegerValidator(minimum, maximum));
		return Integer.parseInt(str);
	}

	@Override
	public int promptFlight(String message) {
		String str = prompt(message, new FlightValidator());
		return Integer.parseInt(str);
	}

	@Override
	public String prompt(String message, Validator validator) {
		while (true) {
			String value = prompt(message);
			String errorMessage = validator.validate(value);
			if (errorMessage == null) {
				return value;
			}
			println(errorMessage);
		}
	}

	@Override
	public LocalDateTime promptDate(String prompt, DateValidator validator) {
		while (true) {
			String value = prompt(prompt);
			String errorMessage = validator.validate(value);
			if (errorMessage == null) {
				return validator.getDate();
			}
			println(errorMessage);
		}
	}
}
