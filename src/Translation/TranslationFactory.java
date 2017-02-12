package Translation;

import java.util.ArrayList;

import command.AbstractFactory;
import command.CommandABS;

public class TranslationFactory extends AbstractFactory {
	
	@Override
	public CommandABS getCommand(String og) {
		String []temp = og.split("\\s");
		String command = temp[1];
		
		int move = temp[0].length() + 1 + temp[1].length() + 1;
		
		String text = og.substring(move);
		TranslationCommand cmd = new TranslationCommand(command, text);
		
		return cmd;
	}
	
}
