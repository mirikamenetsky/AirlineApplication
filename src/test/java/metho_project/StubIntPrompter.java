package metho_project;

public class StubIntPrompter implements IntPrompter {

	private final int[] values;
	private int nextIndex;

	public StubIntPrompter(int[] values) {
		this.values = values;
	}

	public int promptUser(String prompt, IntValidator validator) {
		return values[nextIndex++];
	}

}
