import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertEmployee extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		int id = Integer.parseInt(request.getParameter("id"));
		int salary = Integer.parseInt(request.getParameter("salary"));
		String department = request.getParameter("department");
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management","root","root123");
			PreparedStatement pd = con.prepareStatement("INSERT INTO employee VALUES(?,?,?,?)");
			pd.setInt(1, id);
			pd.setString(2, name);
			pd.setInt(3, salary);
			pd.setString(4, department);
			
			int rs = pd.executeUpdate();
			if(rs>0) {
				response.sendRedirect(request.getContextPath() +  "/GetEmployees");
			}
			
		}catch(Exception e) {
			out.write(e.getMessage());
		}
	}

}
