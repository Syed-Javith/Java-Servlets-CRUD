import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetEmployees extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
        out.write("hello");
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management","root","root123");
			PreparedStatement pd = con.prepareStatement("SELECT * FROM employee");
			ResultSet rs = pd.executeQuery();
			out.write("<table>");
			while(rs.next()){
				out.write("<tr><td>" + rs.getInt(1) + "</td><td>" + 
			rs.getString(2) + "</td><td>"+ rs.getInt(3)+
			"</td><td>" + rs.getString(4) + "</td><td>" + 
			
						"<form action='DeleteEmployee'><input type='hidden' name='id' value='" + rs.getInt(1) + "'/><input type='submit' value='Delete' /></form>"
						+ "</td></tr>");
			}
			out.write("</table>");
			
		}catch(Exception e) {
			out.write(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	

}
// import java.io.*;
// import javax.servlet.*;
// import javax.servlet.http.*;
// import java.sql.*;

// public class GetEmployees extends HttpServlet {
//     public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         response.setContentType("text/html");
//         PrintWriter out = response.getWriter();

//         // Establish a database connection
        
//         try
//         {
//             Class.forName("com.mysql.jdbc.Driver");
//             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/protfolio","root", "root123");

//             PreparedStatement pd =con.prepareStatement("Select * from project");
//             ResultSet rs = pd.executeQuery();

//             while (rs.next()) 
//             {
//                 out.println(rs.getString("title"));    
//                 out.println(rs.getString("description"));    
//                 out.println(rs.getString("link"));    
//             }
//         }
//         catch (Exception e) {
//             e.printStackTrace();
// 			out.write(e.getMessage());
//         } 
//     }
// }
