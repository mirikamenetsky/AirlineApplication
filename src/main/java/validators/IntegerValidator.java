package validators;

public class IntegerValidator implements Validator {

	private final int minimum;
	private final int maximum;

	public IntegerValidator(int minimum, int maximum) {
		this.minimum = minimum;
		this.maximum = maximum;
	}

	@Override
	public String validate(String value) {
		try {
			int i = Integer.parseInt(value);
			if (i < minimum || i > maximum) {
				return "Please enter an integer value between " + minimum + " and " + maximum + " inclusive";
			}
			return null;
		} catch (NumberFormatException e) {
			return "Please enter an integer value";
		}

	}

}
