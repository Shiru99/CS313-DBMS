import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler {
	Connection connection;
	int noOfStudentsPassed = 0;
	int totalStudentsInGradeReport = 0;

	public DatabaseHandler (String database, String username, String password) {
		try (connection = DriverManager.getConnection(database, username, password)) {

		}	catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}

	public boolean isConnected() {
		return connection != null && connection.isClosed() == false;
	}

	public void increaseTotalStudentCountInGradeReport() {
		totalStudentsInGradeReport += 1;
	}

	public boolean courseExists(String course_id) {
		PreparedStatement checkCourse = connection.prepareStatement("SELECT COUNT(*) FROM course WHERE course_id = ?");
		
		checkCourse.setString(1, course_id);
    	ResultSet countCourse = checkCourse.executeQuery();
    	countCourse.next();

    	return countCourse.getInt("count") > 0;
	}

	public boolean studentExists(String student_id) {
		PreparedStatement checkStudent = connection.prepareStatement("SELECT COUNT(*) FROM takes WHERE id = ?");

		checkStudent.setString(1, student_id);
		ResultSet countStudent = checkStudent.executeQuery();
		countStudent.next();

		return countStudent.getInt("count") > 0;
	}

	public int updateGrade(String grade, String student_id) {
		PreparedStatement updateGrade = connection.prepareStatement("UPDATE takes SET grade = ? WHERE id = ?");
		// PreparedStatement updateTotalStudentCredits = connection.prepareStatement("UPDATE student SET tot_cred = ? WHERE id = ?");
		
		updateGrade.setString(1, grade);
		updateGrade.setString(2, id);
		
		return updateGrade.executeUpdate();
	}

	public int getCreditsToUpdate(String student_id, String course_id) {
		PreparedStatement getCredits = connection.prepareStatement("SELECT credits FROM course WHERE course_id = ?");

		getCredits.setString(1, course_id);
		ResultSet noOfCredits = getCredits.executeQuery();
		noOfCredits.next();
		int courseCredits = Integer.parseInt(noOfCredits.getString("credits"));
		
		PreparedStatement getTotalStudentCredits = connection.prepareStatement("SELECT tot_cred FROM student WHERE id = ?");
		
		getTotalStudentCredits.setString(1, student_id);
		ResultSet totalCreditsRow = getTotalStudentCredits.executeQuery();
		totalCreditsRow.next();
		return totalCreditsRow.getInt("tot_cred") + courseCredits;
	}

	public int updateTotalStudentCredits(int creditsToUpdate) {
		PreparedStatement updateTotalStudentCredits = connection.prepareStatement("UPDATE student SET tot_cred = ? WHERE id = ?");
		updateTotalStudentCredits.setInt(1, creditsToUpdate);
		updateTotalStudentCredits.setString(2, id);
		return updateTotalStudentCredits.executeUpdate();
	}
}