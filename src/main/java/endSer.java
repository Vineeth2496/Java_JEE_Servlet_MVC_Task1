

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class endSer extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession hs=request.getSession();
		
		hs.invalidate();
		
		PrintWriter out=response.getWriter();
		
		out.println("<html>");
		out.println("<center>");
		
		out.println("<h1 style='color:red;'>Sucessfully Logged</h1>");
		RequestDispatcher rd=request.getRequestDispatcher("login.html");
		rd.include(request, response);
		out.println("</center>");
		out.println("</html>");
	
	}

}
