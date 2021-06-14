package Home;

import java.io.*;
import java.sql.*;

public class Main {

	public static void main(String[] args) {

		String dbURL = "jdbc:postgresql://localhost:5432/university";
		String user = "universitydb0015";
		String pass = "123";

		try (Connection connection = DriverManager.getConnection(dbURL, user, pass)) {
			System.out.println("Connected to PostgreSQL database!");

//			try {
//				String SQLfile = "/home/shiru/V Sem/DBMS/Lab/Home Assignment/insertValues.sql";
//				Runtime.getRuntime().exec("psql -f '" + SQLfile + "'");
//				System.out.println("SQL file Loaded Successfully !");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

			GradeReport GR = new GradeReport(connection);

			connection.close();

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
