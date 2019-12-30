package metho_project;

import java.time.LocalDateTime;

public class StubDatePrompter implements DatePrompter{
	
	private final LocalDateTime [] values;
	private int nextIndex;
	
	public StubDatePrompter(LocalDateTime[] values) {
		this.values = values;
	}
	
	public LocalDateTime promptUser(String prompt, DateValidator validator) {
		return values[nextIndex++];
	}

}
