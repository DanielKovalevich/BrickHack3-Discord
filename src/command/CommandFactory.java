package command;

public class CommandFactory {
	
	private static CommandFactory instance = null;
	private CommandINF cmd;
	
	public static CommandFactory getInstance() {
		if (CommandFactory.instance == null) {
			CommandFactory.instance = new CommandFactory();
		}
		
		return CommandFactory.instance;
	}
	
	public void createNewCommand(String s){
		
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
		
		}
		
		
		
	}
	
	//
	//	You are able to access the current
	//
	public CommandINF getCurrentCommand(){
		return cmd;
	}
	
	
	
}
