package test;

	import java.io.IOException;
	import java.io.PrintWriter;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;

	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	@WebServlet("/searchlink")
	public class Search extends HttpServlet{
		
		Connection con;
		@Override
		public void init() throws ServletException {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/practice?user=root&password=tiger");
			} 
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String name=req.getParameter("name");
			
			PreparedStatement pstmt=null;
			ResultSet rs;
			
			PrintWriter pw=resp.getWriter();
			String query="select * from phone_info where name=?";
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, name);
				rs=pstmt.executeQuery();
				pw.print("<table border='2'>");
				pw.print("<tr>");
				pw.print("<th>ID</th>");
				pw.print("<th> NAME </th>");
				pw.print("<th>NUMBER</th> ");
				
				pw.print("</tr");
				pw.print("</tr>");
				while(rs.next())
				{
					pw.print("<tr>");
					pw.print("<td>"+rs.getInt(1)+"</td>");
					pw.print("<td>"+rs.getString(2)+"</td>");
					pw.print("<td>"+rs.getInt(3)+"</td>");
					
					pw.print("</tr>");
					
				}
				pw.print("Search Result By Accepting Name");
				pw.print("</table>");
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
