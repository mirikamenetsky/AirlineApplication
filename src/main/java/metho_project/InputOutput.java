package metho_project;

import java.io.InputStream;
import java.io.PrintStream;

public class InputOutput {

	protected static InputStream inputStream;
	protected static PrintStream printStream;
	
	public void setPrintStream(PrintStream printStream) {
		this.printStream = printStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
