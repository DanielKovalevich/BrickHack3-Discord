package core;

import javax.security.auth.login.LoginException;

import command.CommandListener;
import contentmoderation.ContentModerationCore;
import contentmoderation.TextModerationCore;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import users.UserCore;

public class BotCore {
	
	private Configurations config = null;
	private JDA jda;
	
	public BotCore() {
		this.config = new Configurations();
		
		//Create the JDA instance
		this.createInstance();
		this.attachCallbacks();
		
	}
	
	private void createInstance()
	{
		try {

		
			this.jda = new JDABuilder(AccountType.BOT)
					.setToken("MjgwMDU0MzMyNDY2OTIxNDcy.C4D3Vg.dVQ02hg61QkX7tgLMX08ZGwA2W0")
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
		
		this.jda.addEventListener(new CommandListener());
		this.jda.addEventListener(new ContentModerationCore());
		this.jda.addEventListener(new TextModerationCore());
		this.jda.addEventListener(new UserCore());
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
