package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import core.Configurations;

public class MysqlCore {
	
	protected Connection connection = null;
	
	public MysqlCore() {
		
		//Attempt to load the MYSQL driver
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			
			System.out.println("[Error] Missing MYSQL driver!");
			e.printStackTrace();
			return;
		}
		
		System.out.println("Mysql Driver Registered");
		
		//Attempt to connect to the database
		try {
			
			Configurations configManager = new Configurations();
			
			this.connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/" 
						+ configManager.getPropertyValue("MYSQL_Database_Name")
						, configManager.getPropertyValue("MYSQL_Username")
						, configManager.getPropertyValue("MYSQL_Password"));
			
			//Create all of the SQL tables which do not exist already
			MysqlCreateTables tableEngine = new MysqlCreateTables(connection);
			tableEngine.createMissingDatabaseTables();
		}
		
		catch (SQLException e) {
			
			System.out.println("Connection failed! Check output console");
			e.printStackTrace();
			return;
		}
		
		if(connection == null) {
			
			System.out.println("[Warning] connection to SQL database failed for an unknown reason");
		}
	}
	
	public Connection getMysqlConnection() {
		
		return this.connection;
	}

}
