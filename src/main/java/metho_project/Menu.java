package metho_project;

import io.Prompter;

public class Menu {

	private final Prompter prompter;

	public Menu(Prompter prompter) {
		this.prompter = prompter;
	}

	public int chooseItemMain() {
		prompter.println("\n1. Add Flights");
		prompter.println("2. View Available Flights");
		prompter.println("3. Cancel Flight");
		prompter.println("4. Modify Flight Details");
		prompter.println("5. Advanced Flight Search");
		prompter.println("6. Display Upcoming Flights Within One Week");
		prompter.println("7. Exit");
		int i = prompter.prompt("Please select from options 1-7", 1, 7);
		return i;
	}

	public int chooseItemToModify() {
		prompter.println("\n1. Modify Departing From");
		prompter.println("2. Modify Destination");
		int i = prompter.prompt("Please select option 1 or 2", 1, 2);
		return i;
	}

	public int chooseFlightsToSearch() {
		prompter.println("\n1. Search flights by departure");
		prompter.println("2. Search flights by destination");
		int i = prompter.prompt("Please select option 1 or 2", 1, 2);
		return i;
	}
}
