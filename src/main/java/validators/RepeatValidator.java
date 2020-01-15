package validators;

public class RepeatValidator implements Validator {

	@Override
	public String validate(String value) {
		if (value.isEmpty() || !value.toUpperCase().equals("N") && !value.toUpperCase().equals("Y")
				&& !value.toUpperCase().equals("YES") && !value.toUpperCase().equals("NO")) {
			return "Enter Y or N";
		}
		return null;
	}

}
