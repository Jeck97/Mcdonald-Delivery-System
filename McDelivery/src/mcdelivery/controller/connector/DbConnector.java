package mcdelivery.controller.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection
				("jdbc:mysql://127.0.0.1:3306/mcdonaldsdb?serverTimezone=UTC", "root", "");
		
		return connection;
		
	}

}
