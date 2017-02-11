package command;

import exceptions.CommandNotFoundException;

public class CommandFactory {
	
	private static CommandFactory instance = null;
	private CommandINF cmd;
	
	//
	//	I made this class a singleton because we are probably going to have multiple instances
	//	and or types of this factory. We may end up leaving the singleton implementation up
	//	to the classes that inherit this.
	//
	public static CommandFactory getInstance() {
		if (CommandFactory.instance == null) {
			CommandFactory.instance = new CommandFactory();
		}
		
		return CommandFactory.instance;
	}
	
	public void createNewCommand(String s) throws CommandNotFoundException{
		
		switch(s){
		
			case "SomeCommandName": {
				cmd = null;	//	You want to create the new object of class someCommandName here
							//	This is how you set each of the new classes and add more when you
							//	a new command.
					
				break;
			}
			case "SomeCommandName2": {
				cmd = null;
				break;
			}
			
			default:{
				//	Throw the commandnotfound here.
				throw new CommandNotFoundException();
			}
		
		}
		
		
		
	}
	
	//
	//	You are able to access the current
	//
	public CommandINF getCurrentCommand(){
		return cmd;
	}
	
	
	
}
