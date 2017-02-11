package command;

import Translation.TranslationFactory;

public class FactoryMaker {
	
	public static AbstractFactory getFactory(String s){
		
		s = s.toLowerCase();
		
		AbstractFactory f;
		
		//
		//	This decides what factory to make
		//
		switch(s){
		
			case "translate":{
				
				f= new TranslationFactory();
			}
			
			default:
				f = null;
			
		}
		
		return f;
	}
}
