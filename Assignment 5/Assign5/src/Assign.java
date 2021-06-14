
/**
 * 
 */

/**
 * @author shiru
 *
 */

import java.sql.*;

public class Assign {
	public static void main(String[] args) {

		String dbURL = "jdbc:postgresql://localhost:5432/university";
		String user = "universitydb0015";
		String pass = "123";

		try (Connection connection = DriverManager.getConnection(dbURL,user,pass)) 
		{
//			System.out.println("Connected to PostgreSQL database!");
			
			Statement statement = connection.createStatement();
			
			String query = "SELECT * FROM student"+
							""+
							"";

			ResultSet resultSet = statement.executeQuery(query);
			
			while (resultSet.next())
			{
				System.out.printf("%s %s\n", resultSet.getString("id"), resultSet.getString("name"));
			}
			
		} 
		catch (SQLException e)
		{
			System.out.println("Connection failure.");
			
			e.printStackTrace();
		}
	}
}