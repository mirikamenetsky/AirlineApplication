package validators;

public class RepeatValidator implements Validator {

	@Override
	public String validate(String value) {
		if (!value.equalsIgnoreCase("N") && !value.equalsIgnoreCase("Y")
				&& !value.equalsIgnoreCase("YES") && !value.equalsIgnoreCase("NO")) {
			return "Enter Y or N";
		}
		return null;
	}

}
