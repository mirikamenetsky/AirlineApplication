package metho_project;

import java.time.LocalDateTime;

public interface DateValidator {

	String validate(String value);
	LocalDateTime getDate();
}
