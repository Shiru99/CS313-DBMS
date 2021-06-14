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
		DriverManager.getConnection("jdbc:postgresql://localhost:5432/university", "postgres",
		"crocroaz!")) {
//			System.out.println("Connected to PostgreSQL database!");
			
			String csvFilePath = "grdreport.csv";
			BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
	        String lineText = null;
	        int totalStudentsInGradeReport = 0;
	        int noOfStudentsPassed = 0;
	        while ((lineText = lineReader.readLine()) != null) {
	        	String[] data = lineText.split(",");
	        	String id = data[0];
	        	String course_id = data[1];
	        	String grade = data[5];
	        	
	        	totalStudentsInGradeReport += 1;
	        	PreparedStatement checkCourse = connection.prepareStatement("SELECT COUNT(*) FROM course WHERE course_id = ?");
	    		PreparedStatement checkStudent = connection.prepareStatement("SELECT COUNT(*) FROM takes WHERE id = ?");
	    		
	    		checkCourse.setString(1, course_id);
	        	ResultSet countCourse = checkCourse.executeQuery();
	        	countCourse.next();
	        	if (countCourse.getInt("count") > 0) {
	        		checkStudent.setString(1, id);
	        		ResultSet countStudent = checkStudent.executeQuery();
	        		countStudent.next();
	        		if (countStudent.getInt("count") > 0) {
	        			PreparedStatement updateGrade = connection.prepareStatement("UPDATE takes SET grade = ? WHERE id = ?");
	        			PreparedStatement updateTotalStudentCredits = connection.prepareStatement("UPDATE student SET tot_cred = ? WHERE id = ?");
	        			
	        			updateGrade.setString(1, grade);
	        			updateGrade.setString(2, id);
	        			
	        			int countUpdated = updateGrade.executeUpdate();
	        			
	        			if (!grade.equals("F")) {
	        				
	        				noOfStudentsPassed += 1;
	        				PreparedStatement getCredits = connection.prepareStatement("SELECT credits FROM course WHERE course_id = ?");

	        				getCredits.setString(1, course_id);
	        				ResultSet noOfCredits = getCredits.executeQuery();
	        				noOfCredits.next();
	        				int courseCredits = Integer.parseInt(noOfCredits.getString("credits"));
	        				
	        				PreparedStatement getTotalStudentCredits = connection.prepareStatement("SELECT tot_cred FROM student WHERE id = ?");
	        				
	        				getTotalStudentCredits.setString(1, id);
	        				ResultSet totalCreditsRow = getTotalStudentCredits.executeQuery();
	        				totalCreditsRow.next();
	        				int totalCredits = totalCreditsRow.getInt("tot_cred");
	        				
	        				int creditsToUpdate = totalCredits + courseCredits;            							
	        				updateTotalStudentCredits.setInt(1, creditsToUpdate);
	        				updateTotalStudentCredits.setString(2, id);
	        				int countCreditsUpdated = updateTotalStudentCredits.executeUpdate();
	        			}
	        		}
	        		else {
	        			System.out.println("No student exists with id " + id);
	        		}
	        	}
	        	else {
	        		System.out.println("No course exists with course_id " + course_id);
	        	}
	        }
	        
	        lineReader.close();
            
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
