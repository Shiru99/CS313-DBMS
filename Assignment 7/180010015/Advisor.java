


public class Advisor extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		response.setContentType("text/html;charset=UTF-8");
		
		String Advisor_id = request.getParameter("id");//getting student id as input from index.html page
		
		PrintWriter out = response.getWriter();
		
		Connection conn = null;
		Statement  stmt = null;
		
		String department_name = null;
		
		try {
			
			out.println("<!DOCTYPE html>");//print in the form of HTML code
			out.println("<html>");
			out.println("<head><title>Advisor Query Servlet</title></head>");
			out.println("<body>");
			
			Class.forName("org.postgresql.Driver"); //loading postgres driver
						
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/university","postgres","######");//postgres connection with username and password

			String query = " Select DISTINCT ID,dept_name from advisor,instructor " +
			" where instructor.ID = advisor.i_id AND ID=? ";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, Advisor_id);
			
			ResultSet rset = ps.executeQuery();
			
			int count=0;
			while(rset.next()) {
				department_name=rset.getString("dept_name");//getting student name and storing in a variable
				++count;
			}
			
			out.println("Advisor id is " +Advisor_id+" Department is "+department_name);//printing student id and name
			out.println("<p>==== " + count + " row/s found =====</p>");
			out.println("</body></html>");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			out.close();
			try {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();  // closing connection and statement variables
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}

