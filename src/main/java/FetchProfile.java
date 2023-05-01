

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.MVC_1.Data;
import com.MVC_1.DataLogics;

public class FetchProfile extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		HttpSession hs=request.getSession(false);
		
		if( hs != null)
		{
			out.println("<html>");
			out.println("<center>");			
			out.println("WELCOME TO INDIAN NAVY PORTAL");
			//	Reading data from servlet and forward to Data class
			String email=(String) hs.getAttribute("email");
		
			Data d=new Data();
			d.setEmail(email);
		
			//	calling logic class and keeping Data class object(d) data
		
			DataLogics s=new DataLogics();
			Vector<Data> v=s.profile_logics(d);
		
			// 	Fetching  from v // l
		
			for(Data dd : v)
			{
				out.println("<table border=3>");
				out.println("<tr>");
				out.println("<th>"+"Firstname"+"</th>");
				out.println("<th>"+"Lastname"+"</th>");
				out.println("<th>"+"Email"+"</th>");
				out.println("<th>"+"Password"+"</th>");
				out.println("<th>"+"Stdcode"+"</th>");
				out.println("<th>"+"Phoneno"+"</th>");
				out.println("<th>"+"Gender"+"</th>");
				out.println("<th>"+"Language"+"</th>");
				out.println("<th>"+"File"+"</th>");
				out.println("<th>"+"Image"+"</th>");
				out.println("<th>"+"Sign"+"</th>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>"+dd.getFirstname()+"</td>");
				out.println("<td>"+dd.getLastname()+"</td>");
				out.println("<td>"+dd.getEmail()+"</td>");
				out.println("<td>"+dd.getPassword()+"</td>");
				out.println("<td>"+dd.getStdcode()+"</td>");
				out.println("<td>"+dd.getPhoneno()+"</td>");
				out.println("<td>"+dd.getGender()+"</td>");
				out.println("<td>"+dd.getLanguage()+"</td>");
				out.println("</tr>");
				out.println("</table>");
			}
			out.println("<a href='endSer'>Logout</a>");
			out.println("</center>");
			out.println("</html>");
		/*		Iterator ii=v.iterator();
			for(;ii.hasNext();)
				{
				out.print(ii.next()+", ");
				}
				out.println("<a href='endSer'>Logout</a>");
		*/		
		}
		else
		{
		out.println("<html>");
		out.println("<center>");
		out.println("<h1 style='color:red;'>Enter Username and Password</h1>");
		RequestDispatcher rd=request.getRequestDispatcher("login.html");
		rd.include(request, response);
		out.println("</center>");
		out.println("</html>");
		}
		
	}

}
