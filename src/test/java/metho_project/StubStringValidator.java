package metho_project;

public class StubStringValidator implements StringValidator{

	@Override
	public String validate(String value) {
		if(value.isEmpty()) {
			return "input is required";
		}
		return null;
		
	}
	
	
	

	
}
