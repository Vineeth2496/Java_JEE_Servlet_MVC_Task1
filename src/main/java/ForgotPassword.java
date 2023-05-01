

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MVC_1.Data;
import com.MVC_1.DataLogics;


public class ForgotPassword extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String npass=request.getParameter("npass");
	String opass=request.getParameter("opass");
	
	PrintWriter out=response.getWriter();
	
	Data d=new Data();
	d.setNpass(npass);
	d.setOpass(opass);
	
	DataLogics s=new DataLogics();
	int i=s.forgot_logics(d);
	out.println("Sucessfully Updated :"+i);
	 
	
	}

}
