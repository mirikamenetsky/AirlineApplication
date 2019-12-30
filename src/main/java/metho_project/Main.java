package metho_project;

import java.util.*;
import java.io.PrintStream;
import java.text.ParseException;

public class Main {

	public static List<Flight> upcomingFlights = new ArrayList<>();
	private final PrintStream printStream;
	private final StringPrompter stringPrompter;
	private final IntPrompter intPrompter;
	private final DatePrompter datePrompter;
	private final StringValidator stringValidator;
	private final IntValidator intValidator;
	private Menu menu;
	private Search search;
	private FlightModification fm;
	private Formatter formatter;
	private Calculator calc;
	private DateValidator dateValidator;

	public Main(StringPrompter stringPrompter, StringValidator stringValidator, IntPrompter intPrompter,
			IntValidator intValidator, DatePrompter datePrompter, PrintStream printStream) {
		this.stringPrompter = stringPrompter;
		this.stringValidator = stringValidator;
		this.intPrompter = intPrompter;
		this.intValidator = intValidator;
		this.datePrompter = datePrompter;
		this.printStream = printStream;

	}

	public static void main(String[] args) throws ParseException {

		Main m = new Main(new StringPrompterImpl(), new StringValidatorImpl(), new IntPrompterImpl(),
				new IntValidatorImpl(), new DatePrompterImpl(), System.out);
		m.initializeFields();
		m.setInputPrintStream();
		m.loop();
	}

	private void initializeFields() {
		menu = new Menu();
		formatter = new Formatter();
		SystemClock sc = new RealSystemClock();
		dateValidator = new DateValidatorImpl(sc);
		search = new Search(stringPrompter, sc, System.out, formatter, stringValidator, datePrompter, dateValidator);
		calc = new Calculator();
		fm = new FlightModification(stringPrompter, stringValidator, datePrompter, dateValidator, intPrompter,
				intValidator, calc, search);

	}

	public void loop() throws ParseException {
		
		int choice = 0;
		while (choice < 7) {
			choice = menu.chooseItemMain();
			executeOperation(choice);
		}
	}

	public void setInputPrintStream() {
		menu.setInputStream(System.in);
		menu.setPrintStream(System.out);
	}

	public void executeOperation(int choice) throws ParseException {

		switch (choice) {
		case 1:
			fm.addFlights();
			break;
		case 2:
			search.viewFlights(upcomingFlights);
			break;
		case 3:
			cancelFlight();
			break;
		case 4:
			modifyFlight();
			break;
		case 5:
			searchForFlights();
			break;
		case 6:
			upcomingFlightsWithinOneWeek();
			break;
		case 7:
			return;
		default:
			printStream.println("That was not an available option");
			loop();
			
		}
	}

	public void cancelFlight() {
		boolean successful = fm.cancelFlight();
		if (successful) {
			printStream.println("Flight removed successfully");
		} else {
			printStream.println("Flight not Found");
		}
	}

	public void upcomingFlightsWithinOneWeek() {
		List<Flight> flights = search.upcomingFlightsWithinOneWeek();
		search.viewFlights(flights);
	}

	public void modifyFlight() {
		int flightNum = fm.getFlightNumFromUser();
		int index = search.findFlight(flightNum);
		if (index == -1) {
			printStream.println("Flight does not exist");
			return;
		}
		int choice = menu.chooseItemToModify();
		fm.executeModificationChoice(choice, index);
		printStream.println("Flight modified successfully");
	}

	public void searchForFlights() {
		int choice = menu.flightSearch();
		executeFlightSearchChoice(choice);
	}

	public void executeFlightSearchChoice(int choice) {
		switch (choice) {
		case 1:
			search.searchByDeparture();
			break;
		case 2:
			search.searchByDestination();
			break;
		default:
			return;
		}
	}

}
