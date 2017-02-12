package command;

import Translation.TranslationFactory;
import users.UserFactory;

public class FactoryMaker {
	
	public static AbstractFactory getFactory(String s){
		
		s = s.toLowerCase();
		
		AbstractFactory f;
		
		//
		//	This decides what factory to make
		//
		switch(s){
		
			case "translate":{
				
				f = new TranslationFactory();
				break;
			}
			
			case "user":{
				
				f = new UserFactory();
				break;
			}
			
			default:
				f = null;
				break;
			
		}
		
		return f;
	}
}
