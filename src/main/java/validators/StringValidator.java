package validators;

public class StringValidator implements Validator {

	@Override
	public String validate(String value) {
		if (value.isEmpty()) {
			return "input is required";
		}
		return null;
	}

}
