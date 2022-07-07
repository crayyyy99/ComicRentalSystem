package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
	public static Connection doConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comicrentalsystem?useSSL=false","root","abc123");//"host::port/database, user, password"
		return conn;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(DatabaseConnection.doConnection());
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	
	}
	
}
