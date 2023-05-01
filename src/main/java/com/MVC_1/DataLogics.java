package com.MVC_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class DataLogics {

	public static Connection mycon()
	{
		Connection con=null;
		try
		{
			FileInputStream fi=new FileInputStream("C:\\Users\\vinee\\OneDrive\\Desktop\\Java\\Practice\\Collections Frame Works\\com.MVC_1\\jdbc.properties");
			Properties p=new Properties();
			p.load(fi);
			Class.forName(p.getProperty("jdbc.driver")).newInstance();
			con=DriverManager.getConnection(p.getProperty("jdbc.url"), p.getProperty("jdbc.username"), p.getProperty("jdbc.password"));
			System.out.println(con);
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;
		
	}
	
	public int Register_logics(Data d)
	{
		Connection con=DataLogics.mycon();
		int res=0;
		System.out.println(con);
		try
		{
			//	Data Sending to DataBase through pst
			PreparedStatement pst=con.prepareStatement("insert into navy values(?,?,?,?,?,?,?,?,?,?,?)");
			pst.setString(1, d.getFirstname());
			pst.setString(2, d.getLastname());
			pst.setString(3, d.getEmail());
			pst.setString(4, d.getPassword());
			pst.setString(5, d.getStdcode());
			pst.setString(6, d.getPhoneno());
			pst.setString(7, d.getGender());
			pst.setString(8, d.getLanguage());
			FileReader fr=new FileReader(d.getCv());
			pst.setCharacterStream(9, fr);
			
		//	String img=d.getImage();
			FileInputStream fi=new FileInputStream(d.getImage());
			pst.setBinaryStream(10, fi);
			
		//	String sign=d.getSign();
			FileInputStream fi1=new FileInputStream(d.getSign());
			pst.setBinaryStream(11, fi1);
			
			//  Updation
			int i=pst.executeUpdate();
			
			res=i;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return res;
		}
		return res; 
	}
	
	public Vector login_logics()
	{
		Vector v=new Vector();
		try
		{
			FileInputStream fi=new FileInputStream("C:\\Users\\vinee\\OneDrive\\Desktop\\Java\\Practice\\Collections Frame Works\\com.MVC_1\\jdbc.properties");
			Properties p=new Properties();
			p.load(fi);
			Class.forName(p.getProperty("jdbc.driver"));
			RowSetFactory rf=RowSetProvider.newFactory();
			JdbcRowSet jr=rf.createJdbcRowSet();
			jr.setUrl(p.getProperty("jdbc.url"));
			jr.setUsername(p.getProperty("jdbc.username"));
			jr.setPassword(p.getProperty("jdbc.password"));
			jr.setCommand("select email,password from navy");
			jr.execute();
			
			for(;jr.next();)
			{
				v.add(jr.getString("email"));
				v.add(jr.getString("password"));	
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return v;
	}
	
	public Vector profile_logics(Data d)
	{
		Vector v=new Vector();
		try
		{
			FileInputStream fi=new FileInputStream("C:\\Users\\vinee\\OneDrive\\Desktop\\Java\\Practice\\Collections Frame Works\\com.MVC_1\\jdbc.properties");
			Properties p=new Properties();
			p.load(fi);
			Class.forName(p.getProperty("jdbc.driver"));
			RowSetFactory rf=RowSetProvider.newFactory();
			JdbcRowSet jr=rf.createJdbcRowSet();
			jr.setUrl(p.getProperty("jdbc.url"));
			jr.setUsername(p.getProperty("jdbc.username"));
			jr.setPassword(p.getProperty("jdbc.password"));
			jr.setCommand("select * from navy where email=?");
			jr.setString(1, d.getEmail());
			jr.execute();
			
			for(;jr.next();)
			{
				d.setFirstname(jr.getString("firstname"));
				d.setLastname(jr.getString("lastname"));
				d.setEmail(jr.getString("email"));
				d.setPassword(jr.getString("password"));
				d.setStdcode(jr.getString("stdcode"));
				d.setPhoneno(jr.getString("phoneno"));
				d.setGender(jr.getString("gender"));
				d.setLanguage(jr.getString("language"));
				
				v.add(d);
				
				Reader r=jr.getCharacterStream("cv");
				for(int in; (in=r.read())!=-1; in++)
				{
					System.out.print((char)in);
				}
				
				InputStream ii=jr.getBinaryStream("img");
				File f1=new File("image.jpg");
				FileOutputStream fo1=new FileOutputStream(f1);
				for(int in; (in=ii.read())!=-1; in++)
				{
					fo1.write(in);
				}
				System.out.println(f1.getAbsolutePath());
				
				
				InputStream ii1=jr.getBinaryStream("sign");
				File f2=new File("sign.jpg");
				FileOutputStream fo2=new FileOutputStream(f2);
				for(int in; (in=ii1.read())!=-1; in++)
				{
					fo1.write(in);
				}		
				System.out.println(f2.getAbsolutePath());
				
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return v;
	}

	public int forgot_logics(Data d)
	{
		Connection con=DataLogics.mycon();
		int res=0;
		System.out.println(con);
		try
		{
			PreparedStatement pst=con.prepareStatement("update navy set password=? where password=?");
			pst.setString(1, d.getNpass());
			pst.setString(2, d.getOpass());
			int i=pst.executeUpdate();
			res=i;
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}
}
