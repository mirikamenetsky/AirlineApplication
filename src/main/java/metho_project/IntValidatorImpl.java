package metho_project;

public class IntValidatorImpl implements IntValidator {

	@Override 
	public String validate(int value) {
		if(value < 0) {
			return "negative number is not valid";
		}
		return null;
	}

}
