package metho_project;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class InputOutput {

	protected static InputStream inputStream;
	protected static PrintStream printStream;
	protected static Scanner scanner;
	
	public void setPrintStream(PrintStream printStream) {
		this.printStream = printStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
		scanner = new Scanner(inputStream);
	}
}
