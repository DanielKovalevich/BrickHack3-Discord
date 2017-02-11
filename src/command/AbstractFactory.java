package command;

import Translation.TranslationCommand;

public abstract class AbstractFactory {
	
	public abstract TranslationCommand getTranslation(String s);
	//
	//	This is where you will add in new translation types
	//
	
}
