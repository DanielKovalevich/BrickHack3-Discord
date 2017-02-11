package core;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class BotCore {
	
	private JDA jda;
	
	public BotCore() {
		
		//Create the JDA instance
		this.createInstance();
		this.attachCallbacks();

	}
	
	private void createInstance()
	{
		try {

		
			this.jda = new JDABuilder(AccountType.BOT)
					.setToken("BOT TOKEN")
					.buildBlocking();
		} catch (IllegalArgumentException | RateLimitedException e) {
		
			e.printStackTrace();
		}
		
		catch (LoginException ex) {
			
			System.out.println("[Fatal] Unable to connect with the specified token!");
			System.exit(1);
		}
		
		catch (InterruptedException ex) {
			
			System.out.println("[Fatal] Interrupted while trying to connect!");
			System.exit(1);
		}
		
	}
	
	
	private void attachCallbacks() {
		
		//this.jda.addEventListener(new CommandListener());
	}
	
	
	
	private void shutdown() {
		
		this.jda.shutdown();
	}

	public static void main(String[] args) {
		
		//Create the new instance
		BotCore bot = new BotCore();
		
		//Add the shutdown hook
		Runtime.getRuntime().addShutdownHook(new Thread() {
			
			@Override public void run() {
				bot.shutdown();
			}
		});
	}
	

}