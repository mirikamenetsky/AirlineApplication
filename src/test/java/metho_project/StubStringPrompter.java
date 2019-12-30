package metho_project;

public class StubStringPrompter implements StringPrompter{
	
	private final String [] values;
	private int nextIndex;
	
	public StubStringPrompter(String[] values) {
		this.values = values;
	}
	
	public String promptUser(String prompt, StringValidator validator) {
		return values[nextIndex++];
	}
}
