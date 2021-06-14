package Home;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class readCSV {
public static void main(String[] args) throws IOException {
		
		try (Connection connection =
		DriverManager.getConnection("jdbc:postgresql://localhost:5432/university", "universitydb0017",
		"password"))  {
			// System.out.println("Connected to PostgreSQL database!");
			
			String csvFilePath = "/home/harshraj22/Desktop/lab/Home Assignment/grdreport.csv";
			CsvReader reader = new CsvReader(csvFilePath);

			DatabaseHandler database = new DatabaseHandler("jdbc:postgresql://localhost:5432/university", "universitydb0017", "password");

	        int totalStudentsInGradeReport = 0;
	        int noOfStudentsPassed = 0;

	        while (reader.hasNextLine()) {

	        	String id = reader.getId();
	        	String course_id = reader.getCourseId();
	        	String grade = reader.getGrade();
	        	
	        	totalStudentsInGradeReport += 1;


	      //   	PreparedStatement checkCourse = connection.prepareStatement("SELECT COUNT(*) FROM course WHERE course_id = ?");
	    		// PreparedStatement checkStudent = connection.prepareStatement("SELECT COUNT(*) FROM takes WHERE id = ?");
	    		
	    		// checkCourse.setString(1, course_id);
	      //   	ResultSet countCourse = checkCourse.executeQuery();
	      //   	countCourse.next();

	      //   	if (countCourse.getInt("count") <= 0) {
	      //   		System.out.println("No course exists with course_id " + course_id);
	      //   		continue;
	      //   	}
	        	if (database.courseExists(course_id) == false)
	        		continue;
	        	else if (database.studentExists(id) == false)
	        		continue;

        		// checkStudent.setString(1, id);
        		// ResultSet countStudent = checkStudent.executeQuery();
        		// countStudent.next();

        		// if (countStudent.getInt("count") <= 0) {
        		// 	System.out.println("No student exists with id " + id);
        		// 	continue;
        		// }
        		
    		
    			// PreparedStatement updateGrade = connection.prepareStatement("UPDATE takes SET grade = ? WHERE id = ?");
    			
    			// updateGrade.setString(1, grade);
    			// updateGrade.setString(2, id);
    			
    			// int countUpdated = updateGrade.executeUpdate();
    			int countUpdated = database.updateGrade();
    			
    			if (grade.equals("F"))
    				continue;

				
				noOfStudentsPassed += 1;

				// PreparedStatement getCredits = connection.prepareStatement("SELECT credits FROM course WHERE course_id = ?");

				// getCredits.setString(1, course_id);
				// ResultSet noOfCredits = getCredits.executeQuery();
				// noOfCredits.next();
				// int courseCredits = Integer.parseInt(noOfCredits.getString("credits"));
				
				// PreparedStatement getTotalStudentCredits = connection.prepareStatement("SELECT tot_cred FROM student WHERE id = ?");
				
				// getTotalStudentCredits.setString(1, id);
				// ResultSet totalCreditsRow = getTotalStudentCredits.executeQuery();
				// totalCreditsRow.next();
				// int totalCredits = totalCreditsRow.getInt("tot_cred");


				
				// int creditsToUpdate = totalCredits + courseCredits;     
				int creditsToUpdate = database.getCreditsToUpdate(id, course_id);       							
	
				// PreparedStatement updateTotalStudentCredits = connection.prepareStatement("UPDATE student SET tot_cred = ? WHERE id = ?");
				// updateTotalStudentCredits.setInt(1, creditsToUpdate);
				// updateTotalStudentCredits.setString(2, id);
				// int countCreditsUpdated = updateTotalStudentCredits.executeUpdate();
				int countCreditsUpdated = database.updateTotalStudentCredits(creditsToUpdate);
    			
	        }
	        
            int noOfStudentsFailed = totalStudentsInGradeReport - noOfStudentsPassed;

            System.out.println("No. of Students in grade report: " + totalStudentsInGradeReport);
            System.out.println("No. of Students passed: " + noOfStudentsPassed);
            System.out.println("No. of Students with FF grade: " + noOfStudentsFailed);
            
            connection.close();
			
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}
}
