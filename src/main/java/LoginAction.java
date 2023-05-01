

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.MVC_1.DataLogics;

public class LoginAction extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String email=request.getParameter("em");
		String password=request.getParameter("pa");
		
		PrintWriter out=response.getWriter();
		
		//	to get data from DB, then goes to  datalogics
		
		
		DataLogics s=new DataLogics();
		
		Vector v=s.login_logics();
		
		//	the above DB data (v) compare with ui data
		HttpSession hs=request.getSession();
		
		if(v.contains(email)&&v.contains(password))
		{
			hs.setAttribute("email", email);
			hs.setAttribute("pass", password);
			RequestDispatcher rd=request.getRequestDispatcher("FetchProfile");
			rd.forward(request, response);
		}
		else
		{
			out.println("<html>");
			out.println("<center>");
			
			out.println("<h1 style='color:red;'>Invalid Username or Password</h1>");
			RequestDispatcher rd=request.getRequestDispatcher("login.html");
			rd.include(request, response);
			out.println("</center>");
			out.println("</html>");
		}
		
		
		
	}
}
