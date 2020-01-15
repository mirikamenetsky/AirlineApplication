package io;

import java.time.LocalDateTime;
import validators.*;

public interface Prompter {

	void print(String message);

	void println(String message);

	String prompt(String message);

	String prompt(String message, Validator validator);

	int prompt(String message, int minimum, int maximum);

	int promptFlight(String message);

	LocalDateTime promptDate(String string, DateValidator validator);

}
