package metho_project;

public class StringValidatorImpl implements StringValidator{

	@Override
	public String validate(String value) {
		if(value.isEmpty()) {
			return "input is required";
		}
		return null;
		
	}
	
	
	

	
}
