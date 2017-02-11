package Translation;

import command.AbstractFactory;
import command.CommandINF;

public class TranslationFactory extends AbstractFactory {
	
	@Override
	public CommandINF getCommand(String s) {
		TranslationCommand cmd = null;
		
		//
		//	We will build a new command here based on what string we have here.
		//
		
		return cmd;
	}
	
}
