import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Temp
{
	public static void main(String[] args) 
	{
		String dbURL = "jdbc:postgresql://localhost:5432/university";
		String user = "postgres";
		String pass = "#######";

		String fail = "sorry! Connection failure occured.";
		String Sucsesfull = "process successful";

		try(Connection connection = DriverManager.getConnection(dbURL,user,pass)) 
		{
			System.out.println("Java JDBC PostgreSQL Example");
			System.out.println("Connected to PostgreSQL database!");
			
			PreparedStatement smt = connection.prepareStatement("insert into instructor values(?, ?, ?, ?)");

			String teach = "MyName";
			String sub = "Comp. Sci.";
			
			smt.setInt(1, 20943);
			smt.setString(2, teach);
			smt.setString(3, sub);
			smt.setInt(4, 200000);
			
			int count = smt.executeUpdate();

			String f1 = " record updated";
			System.out.println(count + f1);
			System.out.println(Sucsesfull);
		}
		catch (SQLException e) 
		{
			System.out.println(fail);
			e.printStackTrace();
		}
	}
}