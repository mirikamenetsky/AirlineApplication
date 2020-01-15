package validators;

public class FlightValidator implements Validator {

	@Override
	public String validate(String value) {
		try {
			int i = Integer.parseInt(value);
			if (i < 1) {
				return "Invalid flight number";
			}
			return null;
		} catch (NumberFormatException e) {
			return "Please enter an integer value";
		}
	}
}
