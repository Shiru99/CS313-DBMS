/**
 * @author shiru
 *
 */

import java.sql.*;

public class Lab6Q2 {
	public static void main(String[] args) {

		String dbURL = "jdbc:postgresql://localhost:5432/university";
		String user = "postgres";
		String pass = "#######";
		
		try (Connection connection = DriverManager.getConnection(dbURL,user,pass)) 
		{
//			System.out.println("Connected to PostgreSQL database!");
			
			PreparedStatement statement = connection.prepareStatement("insert into instructor values(?, ?, ?, ?)");
			statement.setInt(1, 55555);
			statement.setString(2, "WhiteHatJr");
			statement.setString(3, "Comp. Sci.");
			statement.setInt(4, 200000);
			
			int count = statement.executeUpdate();
			System.out.println(count + " records inserted");
			
			
		} 
		catch (SQLException e)
		{
			System.out.println("Connection failure.");
			
			e.printStackTrace();
		}
	}
}
