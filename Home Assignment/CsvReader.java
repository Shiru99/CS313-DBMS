import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CsvReader {
	String filePath;
	String lineText;
	BufferedReader lineReader;

	public CsvReader (String filePath) {
		this.filePath = filePath;
		lineReader = new BufferedReader(FileReader(filePath));



	}

	public boolean hasNextLine() {
		boolean nextLine = (lineText = lineReader.readLine()) != null;
		return nextLine;
	}

	public String getId() {
		return lineText.split(",")[0];
	}

	public String getCourseId() {
		return lineText.split(",")[1];
	}

	public String getGrade() {
		return lineText.split(",")[5];
	}
}