import java.io.*;
import java.sql.*;

public class JDBC {

	public static void main(String[] args) {

		String dbURL = "jdbc:postgresql://localhost:5432/university";
		String user = "universitydb0015";
		String pass = "123";

		try (Connection connection = DriverManager.getConnection(dbURL, user, pass)) {

			System.out.println("Connected to PostgreSQL database!");

			String query = " select course_id,title from ( "+" select course.course_id,title,count(course.course_id) as c from course,takes " + " where course.course_id = takes.course_id and takes.year='2017' and semester='Spring' " + " group by course.course_id " + " ORDER BY c DESC, title  ASC " + " ) " + " limit 1; " 
			;

			ResultSet resultSet = statement.executeQuery(query);
			
			while (resultSet.next())
			{
				System.out.printf("%s %s\n", resultSet.getString("course_id"), resultSet.getString("title"));
			}



		} catch (SQLException e) {
			System.out.println("Connection failure.");

			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
