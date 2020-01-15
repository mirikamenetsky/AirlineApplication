package miriMocks;

import metho_project.Prompter;

public class PrompterStub implements Prompter {

	private final String [] values;
	private int nextIndex;
	
	public PrompterStub(String[] values) {
		this.values = values;
	}
	
	public String promptUser(String prompt) {
		return values[nextIndex++];
	}
	
}
