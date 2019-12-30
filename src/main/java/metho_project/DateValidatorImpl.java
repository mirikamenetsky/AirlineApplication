package metho_project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateValidatorImpl implements DateValidator {

	private SystemClock systemClock;
	private LocalDateTime date;

	DateValidatorImpl(SystemClock systemClock) {
		this.systemClock = systemClock;
	}

	public String validate(String value) {
		if (value.isEmpty()) {
			return "Date is required";
		}
		this.date = parse(value);
		if (date.isBefore(systemClock.getCurrentDate())) {
			return "Invalid date";
		}
		return null;
	}

	public LocalDateTime parse(String value) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime date = LocalDateTime.parse(value, formatter);
		return date;
	}

	public LocalDateTime getDate() {
		return date;
	}

}
