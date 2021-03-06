


import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/IssueServlet")
public class IssueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public IssueServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
	
		//getting input values from jsp page
		int studentId = Integer.parseInt(request.getParameter("s_id"));
		int bookId = Integer.parseInt(request.getParameter("b_id"));
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
		java.sql.Date issueDate = new java.sql.Date(date.getTime()) ;

		Connection con = null;
 		String url = "jdbc:postgresql://localhost:5432/library"; //PostgreSQL URL and followed by the database name
 		String username = "postgres"; //PostgreSQL username
 		String password = "crocroaz!"; //PostgreSQL password
		
		Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection(url, username, password); //attempting to connect to PostgreSQL database
 		System.out.println("Printing connection object "+con);

 		//Prepared Statement to check student's existence
		PreparedStatement checkStudent = con.prepareStatement("select count(*) from student where student_id = ?");
 		checkStudent.setInt(1, studentId);
		ResultSet resultSet = checkStudent.executeQuery();
		resultSet.next();
		
		if (resultSet.getInt("count") != 0) {
			//Prepared Statement to add issue data
			PreparedStatement addIssue = con.prepareStatement("insert into issue values(?, ?, ?)");
			addIssue.setInt(1, studentId);
			addIssue.setInt(2, bookId);
			addIssue.setDate(3, issueDate);
			int result = addIssue.executeUpdate();
				
			//Checks if insert is successful.If yes,then redirects to Result.jsp page 
			if(result>0) {				
				RequestDispatcher rd = request.getRequestDispatcher("IssueResult.jsp");
				rd.forward(request, response);
			}
		}
		else {
			System.out.println("Student does not exist!");
		}

		}
		 catch (Exception e) 
 		{
 			e.printStackTrace();
 		}

	
	}


}


