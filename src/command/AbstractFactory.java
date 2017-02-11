package command;

import Translation.TranslationCommand;
import exceptions.CommandNotFoundException;

public abstract class AbstractFactory {
	
	//
	//	getCommand allows you based on which factory you have created then create the
	//	command that you need to run. Some commands will need different parameters.
	//
	public abstract CommandABS getCommand(String s) throws CommandNotFoundException;
	
}
