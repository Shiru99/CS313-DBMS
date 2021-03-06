import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Servlet implementation class IssueServlet
 */
@WebServlet("/IssueServlet")
public class IssueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IssueServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			Connection conect = null;

			Class.forName("org.postgresql.Driver");

			conect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library", "universitydb0015", "123");
			System.out.println("Connected to PostgreSQL database!");

			String StudentID = request.getParameter("StudentID");
			String BookID = request.getParameter("BookID");

			java.util.Date TempDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("IssueDate"));
			java.sql.Date IssueDate = new java.sql.Date(TempDate.getTime());

			PreparedStatement StudentStatus = conect
					.prepareStatement("SELECT count(*) FROM student WHERE student_id = ?");
			StudentStatus.setString(1, StudentID);
			ResultSet Output = StudentStatus.executeQuery();
			Output.next();

			if (Output.getInt("count") != 0) { // Student exists
				// Prepared Statement to add issue data
				PreparedStatement addIssue = conect.prepareStatement("INSERT INTO issue VALUES(?, ?, ?)");
				addIssue.setString(1, StudentID);
				addIssue.setString(2, BookID);
				addIssue.setDate(3, IssueDate);

				int SuccessStatus = addIssue.executeUpdate();

				if (SuccessStatus != 0) {
					RequestDispatcher SuccessPage = request.getRequestDispatcher("IssueResult.jsp");
					SuccessPage.forward(request, response);
				}

			} else {
				System.out.println("This student does not exist.!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
