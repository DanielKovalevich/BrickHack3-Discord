package command;

import Translation.TranslationCommand;
import exceptions.CommandNotFoundException;

public abstract class AbstractFactory {
	
	public abstract CommandINF getCommand(String s) throws CommandNotFoundException;
	//
	//	This is where you will add in new translation types
	//
	
}
