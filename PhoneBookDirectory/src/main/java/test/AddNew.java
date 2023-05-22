	package test;

	import java.io.IOException;
	import java.io.PrintWriter;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.sql.*;

	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	@WebServlet("/addlink")
	public class AddNew  extends HttpServlet{
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
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String name=req.getParameter("name");
			String number=req.getParameter("number");
			
			int number1=Integer.parseInt(number);
			
			PreparedStatement pstmt=null;
			String query="insert into phone_info(name,number) values(?,?)";
			
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, name);
				pstmt.setInt(2, number1);
				
				int count=pstmt.executeUpdate();
				PrintWriter pw=resp.getWriter();
				pw.print("<h1>Inserted  Successfully"+count+"</h1>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}



