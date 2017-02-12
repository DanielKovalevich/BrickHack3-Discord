package users;

import command.AbstractFactory;
import command.CommandABS;
import exceptions.CommandNotFoundException;

public class UserFactory extends AbstractFactory {

	@Override
	public CommandABS getCommand(String s) throws CommandNotFoundException {
		
		switch(s) {
		
		case "ToggleAutocorrect":
			return new ToggleAutocorrect();
		}
		
		return null;
	}
	
	

}
