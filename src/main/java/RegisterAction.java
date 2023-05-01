

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MVC_1.Data;
import com.MVC_1.DataLogics;

public class RegisterAction extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname=request.getParameter("fn");
		String lastname=request.getParameter("ln");
		String email=request.getParameter("em");
		String password=request.getParameter("pa");
		String stdcode=request.getParameter("std");
		String phoneno=request.getParameter("ph");
		String gender=request.getParameter("gn");
		String language=request.getParameter("lan");
		String cv=request.getParameter("cv");
		String image=request.getParameter("img");
		String sign=request.getParameter("sign");
		
		PrintWriter out=response.getWriter();
		
		// the above ui data/values forwarding to 'Data' class of Setter methods
		
		Data d=new Data();
		d.setFirstname(firstname);
		d.setLastname(lastname);
		d.setEmail(email);
		d.setPassword(password);
		d.setStdcode(stdcode);
		d.setPhoneno(phoneno);
		d.setGender(gender);
		d.setLanguage(language);
		d.setCv(cv);
		d.setImage(image);
		d.setSign(sign);
		
		// calling DataLogics methods
		
		DataLogics s=new DataLogics();
		Connection con=DataLogics.mycon();
		out.println(con);
		int i=s.Register_logics(d);
		out.println("Registered update	:	"+i);
		
	/*	out.println(firstname);
		out.println(lastname);
		out.println(email);
		out.println(password);
		out.println(stdcode);
		out.println(phoneno);
		out.println(gender);
		out.println(language);
		out.println(cv);
		out.println(image);
		out.println(sign);
	*/	
		

	}

}
