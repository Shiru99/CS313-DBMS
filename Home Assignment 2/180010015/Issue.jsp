<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Issue a Book</title>
		<style>
			input{
			  margin: 13px;
			}
		</style>
	</head>
	<body>
		<center>
			<h1>Library Management System</h1>
			<h3>Issue Details </h3>
			<form action="IssueServlet" method="post">
				<table style="with: 50%">
					<tr>
						<td>Student ID</td>
						<td>
							<input type="text" name="StudentID"/>
						</td>
					</tr>
					<tr>
						<td>Book ID</td>
						<td>
							<input type="text" name="BookID"/>
						</td>
					</tr>
					<tr>
						<td>Issue Date</td>
						<td>
							<input type="text" name="IssueDate" placeholder="yyyy-MM-dd"/>
						</td>
					</tr>
					<tr>
						<td>Return Date</td>
						<td>
							<input type="text" name="ReturnDate" placeholder="Can be null"/>
						</td>
					</tr>
				</table>
				<input type="submit" value="Submit" />
			</form>
		</center>
	</body>
</html>
