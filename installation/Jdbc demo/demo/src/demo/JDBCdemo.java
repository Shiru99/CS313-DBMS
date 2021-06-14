package demo;

import java.sql.*;

//public class JDBCdemo {
//	public static void main(String[] args) {
//		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/university",
//				"universitydb0015", "123")) {
//			System.out.println("Java JDBC PostgreSQL Example");
//			System.out.println("Connected to PostgreSQL database!");
//			Statement statement = connection.createStatement();
//			System.out.println("Reading student records...");
//			System.out.printf("%s %s", "id", "name");
//			ResultSet resultSet = statement.executeQuery("SELECT * FROM student");
//			while (resultSet.next()) {
//				System.out.printf("%s %s\n", resultSet.getString("id"), resultSet.getString("name"));
//			}
//		} catch (SQLException e) {
//			System.out.println("Connection failure.");
//			e.printStackTrace();
//		}
//	}
//}


/**
 * @author shiru
 *
 */
//package lab6;

import java.sql.*;

public class JDBCdemo {
	public static void main(String[] args) {

		String dbURL = "jdbc:postgresql://localhost:5432/university";
		String user = "postgres";
		String pass = "#######";

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
