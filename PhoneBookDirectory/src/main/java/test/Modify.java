package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class Modify extends HttpServlet {
	
	Connection con;
	public void init() throws ServletException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/practice?user=root&password=tiger");
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		String name=req.getParameter("name");
		String number=req.getParameter("number");
		
		int id1=Integer.parseInt(id);
		int number1=Integer.parseInt(number);
		
		PreparedStatement pstmt=null;
		
		String query="update phone_info set name=?, number=? where id=?";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setInt(3, id1);
			pstmt.setInt(2, number1);
			int count=pstmt.executeUpdate();
			PrintWriter pw=resp.getWriter();
			pw.print("<h1>"+count+"Record Update Successfully</h1>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
