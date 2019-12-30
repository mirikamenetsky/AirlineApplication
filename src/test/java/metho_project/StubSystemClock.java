package metho_project;

import java.time.LocalDateTime;

public class StubSystemClock implements SystemClock {

	private LocalDateTime date;


	public StubSystemClock(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public LocalDateTime getCurrentDate() {
		return date;
	}
}
