import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddServlet() {
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

			String ID = request.getParameter("BookID");
			String Title = request.getParameter("Title");
			String Category = request.getParameter("Category");
			String Author = request.getParameter("Author");

			PreparedStatement BookStatus = conect.prepareStatement("SELECT count(*) FROM book WHERE book_id = ?");
			BookStatus.setString(1, ID);
			ResultSet Output = BookStatus.executeQuery();
			Output.next();

			if (Output.getInt("count") == 0) // Book doesn't exist in Library
			{
				PreparedStatement AddingNewBook = conect.prepareStatement("INSERT INTO book VALUES(?, ?, ?, ?)");
				AddingNewBook.setString(1, ID);
				AddingNewBook.setString(2, Title);

				AddingNewBook.setString(3, Category);
				AddingNewBook.setString(4, Author);
				
				int SuccessStatus = AddingNewBook.executeUpdate();

				if (SuccessStatus != 0) {
					RequestDispatcher SuccessPage = request.getRequestDispatcher("AddResult.jsp");
					SuccessPage.forward(request, response);
				}
			} else {
				System.out.println("Error : Book Already Exists in Library...");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
