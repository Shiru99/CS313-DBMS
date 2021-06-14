/**
 * @author shiru
 *
 */
import java.sql.*;

public class Lab6Q3 {
	public static void main(String[] args) {

		String dbURL = "jdbc:postgresql://localhost:5432/university";
		String user = "postgres";
		String pass = "#######";

		try (Connection connection = DriverManager.getConnection(dbURL,user,pass)) 
		{
//			System.out.println("Connected to PostgreSQL database!");
			
			PreparedStatement statement = connection.prepareStatement("UPDATE instructor SET dept_name = ? WHERE name = ?");

			statement.setString(1, "Math");
			statement.setString(2, "Levine");
			
			int count = statement.executeUpdate();
			System.out.println(count + " records updated");
			
		} 
		catch (SQLException e)
		{
			System.out.println("Connection failure.");
			
			e.printStackTrace();
		}
	}
}

