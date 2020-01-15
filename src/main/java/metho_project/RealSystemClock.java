package metho_project;

import java.time.LocalDateTime;

public class RealSystemClock implements SystemClock {

	@Override
	public LocalDateTime getCurrentDate() {
		return LocalDateTime.now();
	}

}
