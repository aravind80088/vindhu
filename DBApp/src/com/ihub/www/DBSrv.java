package com.ihub.www;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DBSrv extends HttpServlet
{
	protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		String sno=req.getParameter("t1");
		 int no=Integer.parseInt(sno);
		String name=req.getParameter("t2");
		String add=req.getParameter("t3");
		
		Connection con=null;
		PreparedStatement ps=null;
		String qry=null;
		
		int result=0;
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","admin");
			qry="insert into student values(?,?,?)";
			ps=con.prepareStatement(qry);
			
			ps.setInt(1,no);
			ps.setString(2,name);
			ps.setString(3,add);
			
			result=ps.executeUpdate();
			
			
			if(result==0)
				pw.println("<center><h1>No record inserted</h1></center>");
			else
				pw.println("<center><h1> record inserted</h1></center>");
			
			ps.close();
			con.close();
		}
		catch(Exception e)
		{
		   pw.println(e);	
			
		}
	}
}
			
			
			
		
		
		
		
		
		
	


