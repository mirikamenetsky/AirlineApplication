package metho_project;

import java.time.LocalDateTime;

public interface DatePrompter {

	LocalDateTime promptUser(String value, DateValidator validate);
}
