package validators;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import metho_project.SystemClock;

public class DateValidator implements Validator {

	private final SystemClock systemClock;
	private LocalDateTime date;

	public DateValidator(SystemClock systemClock) {
		this.systemClock = systemClock;
	}

	public String validate(String value) {
		if (value.isEmpty()) {
			return "Date is required";
		}
		this.date = parse(value);
		if (date == null)
			return "Invalid format";
		if (date.isBefore(systemClock.getCurrentDate())) {
			return "Invalid date";
		}
		return null;
	}

	public LocalDateTime parse(String value) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		try {
			LocalDateTime date = LocalDateTime.parse(value, formatter);
			return date;
		} catch (DateTimeParseException e) {
			return null;
		}

	}

	public LocalDateTime getDate() {
		return date;
	}

}
