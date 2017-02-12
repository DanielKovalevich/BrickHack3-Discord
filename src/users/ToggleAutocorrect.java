package users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import command.CommandABS;
import data.MysqlCore;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ToggleAutocorrect extends CommandABS {

	@Override
	public void doCommand(MessageReceivedEvent event) {
		
		MysqlCore sql = new MysqlCore();
		Connection connection = sql.getMysqlConnection();
		
		try {
			//Get the users current spellcheck setting
			String currentSettingQuery = "SELECT spellcheckEnabled WHERE discordId = ?";
			PreparedStatement currentSettingStatement = connection.prepareStatement(currentSettingQuery);
			currentSettingStatement.setString(1, event.getAuthor().getId());
			ResultSet currentSettingResult = currentSettingStatement.executeQuery();
			
			int enabledStatus = currentSettingResult.getInt(1);
			int targetStatus = 0;
			
			//If the enabled status is enabled, disable it. Otherwise enable it
			if(enabledStatus == 0)
				targetStatus = 1;
			
			else if(enabledStatus == 1)
				targetStatus = 0;

		
			String query = "UPDATE discordusers SET spellcheckEnabled = ? WHERE discordId = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			
			System.out.println("[Debug] setting user autocorrect status to: " + String.valueOf(targetStatus));
			
			statement.setInt(1, targetStatus);
			statement.setString(2, event.getAuthor().getId());
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
