package metho_project;

import java.util.*;
import io.*;
import validators.*;
import java.text.ParseException;

public class Main {

	public static void main(String[] args) throws ParseException {

		List<Flight> flights = new ArrayList<>();
		Prompter prompter = new PrompterImpl(new InputOutputImpl());
		StringValidator stringValidator = new StringValidator();
		Menu menu = new Menu(prompter);
		Formatter formatter = new Formatter();
		SystemClock sc = new RealSystemClock();
		DateValidator dateValidator = new DateValidator(sc);
		Searcher searcher = new Searcher(prompter, sc, formatter, stringValidator, dateValidator, flights);
		Calculator calc = new Calculator();
		FlightModification fm = new FlightModification(prompter, stringValidator, dateValidator, calc, searcher,
				flights);
		RepeatValidator repeatValidator = new RepeatValidator();
		Program program = new Program(menu, searcher, fm, prompter, repeatValidator,
				new FlightEntryForm(prompter, stringValidator, dateValidator, calc), flights);
		program.loop();
	}

}
