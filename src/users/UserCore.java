package users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import data.MysqlCore;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class UserCore extends ListenerAdapter {
	
	private boolean hasInit = false;
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		if(!hasInit)
			scanGuildForUsers(event);
		
	}
	
	private void scanGuildForUsers(MessageReceivedEvent event){
		
		MysqlCore core = new MysqlCore();
		this.hasInit = true;
		
		Connection databaseConnection = core.getMysqlConnection();
		
		//For each of the users..
		Iterator<Member> members = event.getGuild().getMembers().iterator();
		
		while(members.hasNext()) {
			
			Member currentMember = members.next();
			
			//Insert it into the SQL table if the user doesn't exist
			try {
				String query = "INSERT IGNORE INTO discordusers (discordName, discordId)" + " VALUES(?, ?) ";
				
				PreparedStatement statement = databaseConnection.prepareStatement(query);
				statement.setString(1, currentMember.getUser().getName());
				statement.setString(2, currentMember.getUser().getId());
				
				System.out.println("Adding " + currentMember.getUser().getName());
				
				statement.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			
		}
	}

}
