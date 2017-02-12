package users;

import command.AbstractFactory;
import command.CommandABS;
import exceptions.CommandNotFoundException;

public class UserFactory extends AbstractFactory {

	@Override
	public CommandABS getCommand(String s) throws CommandNotFoundException {
		
		CommandABS temp;
		
		System.out.println(s.substring(6));
		
		switch(s.substring(6).split(" ")[0]) {
		
		case "ToggleAutocorrect":
			temp = new ToggleAutocorrect();
			break;
			
		case "query":
			temp = new BingSearch();
			break;
			
		default:
			temp = null;
			break;
		}
					
		
		return temp; 
	}
	
	

}
