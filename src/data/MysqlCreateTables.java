package data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MysqlCreateTables {
	
	private Connection connection = null;

	public MysqlCreateTables(Connection connection) {

		this.connection = connection;
	}
	
	private Map<String, Boolean> mysqlTableList() {
		
		Map<String, Boolean> requiredTables = new HashMap<String, Boolean>();
		
		//Add each of the table names here which needs to be run. Always set bool to false
		requiredTables.put("UserList", false);
		
		return requiredTables;
	}
	
	private void createTable(Statement connection, String query) {
		
		try {
			
			connection.executeUpdate(query);
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
	}

	public void createMissingDatabaseTables() throws SQLException {
		
		DatabaseMetaData tableMetaData = connection.getMetaData();
		Statement statement = connection.createStatement();
		
		//Get all of the available tables in the MysqlDatabase
		ResultSet availableTables = tableMetaData.getTables(null, null, "%", null);
		
		Map<String, Boolean> requiredTables = mysqlTableList();
		
		while(availableTables.next()) {
			
			if(requiredTables.containsKey(availableTables.getString(3).substring(0, availableTables.getString(3).length() - 5))) {
				
				requiredTables.replace(availableTables.getString(3), true);
			}
		}
		
		for(Entry<String, Boolean> tableElement : requiredTables.entrySet()) {
			
			if(tableElement.getValue().booleanValue() == true)
				break;
			
			switch(tableElement.getKey()) {
			
			case "UserList":
				String tableQuery = 
				"";
			}
		}
		
	}
}
