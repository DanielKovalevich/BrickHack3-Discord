package users;

import command.AbstractFactory;
import command.CommandABS;
import exceptions.CommandNotFoundException;

public class UserFactory extends AbstractFactory {

	@Override
	public CommandABS getCommand(String s) throws CommandNotFoundException {
		
		CommandABS temp;
		
		switch(s.substring(6)) {
		
		case "ToggleAutocorrect":
			temp = new ToggleAutocorrect();
			break;
			
		default:
			temp = null;
			break;
		}
					
		
		return temp; 
	}
	
	

}
