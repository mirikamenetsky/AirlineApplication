package metho_project;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Menu {

	private InputStream inputStream;
	private PrintStream printStream;

	public void setPrintStream(PrintStream printStream) {
		this.printStream = printStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public int chooseItemMain() {
		Scanner scanner = new Scanner(inputStream);
		printStream.println("Please select one of the following options");
		printStream.println("1. Add Flights");
		printStream.println("2. View Available Flights");
		printStream.println("3. Cancel Flight");
		printStream.println("4. Modify Flight Details");
		printStream.println("5. Advanced Flight Search");
		printStream.println("6. Display Upcoming Flights");
		printStream.println("7. Exit");
		int i = scanner.nextInt();
		// clear the buffer
		scanner.nextLine();
		return i;
	}
	
	public int chooseItemToModify() {
		Scanner scanner = new Scanner(inputStream);
		printStream.println("Please select one of the following options");
		printStream.println("1. Modify Departing From");
		printStream.println("2. Modify Destination ");
		int i = scanner.nextInt();
		// clear the buffer
		scanner.nextLine();
		return i;
	}
	
	public int flightSearch() {
		Scanner scanner = new Scanner(inputStream);
		printStream.println("Please select one of the following options");
		printStream.println("1. Search flights by departure");
		printStream.println("2. Search flights by destination");
		int i = scanner.nextInt();
		// clear the buffer
		scanner.nextLine();
		return i;
	}
}
