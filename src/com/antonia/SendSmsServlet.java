package com.antonia;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.sdk.TwilioRestException;

/**
 * Servlet implementation class SendSmsServlet
 */
@WebServlet("/SendSmsServlet")
public class SendSmsServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
     private Connection con = null;
     private Statement st;
 
	    public void init() throws ServletException {
	    	try {
	    		Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost/SmsDBs", "root", "root");
				if (con == null) {
					System.exit(1);
					System.out.println("no data");
				}
			} catch (Exception e) {
				e.printStackTrace();

			} finally {
				System.out.println("Succesfull");
			}
	    	
	    }
	 
	     
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
	    	response.setContentType("text/html");
	        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
	        PrintWriter out= response.getWriter();
	        
	        String number = request.getParameter("number");
	        String text= request.getParameter("text");
      
	        if(!Pattern.matches("^([0+]{1,2}359|0)8[789]\\w{7}$", number)){
	            out.println("<font color=red>Number is not valid bulgarian number!</font>");
	            
	        } else if (text.isEmpty()){
	           out.println("<font color=red>Text field should not be empty!</font>");
	        } else {
	        	try {
	    			SendSms.send("+359" + number.substring(number.length() - 9), text);
	    		} catch (TwilioRestException e) {
	    			e.printStackTrace();
	    		} finally {
	    			out.println("<font color=red>Message was send succesfully!</font>");
	    			try {
						st = (Statement) con.createStatement();
						String sql = "INSERT INTO sendSms (number,text) VALUES (?, ?)";
			       		 
		        		PreparedStatement statement = con.prepareStatement(sql);
		        		statement.setString(1, number);
		        		statement.setString(2, text);
		        		 
		        		int rowsInserted = statement.executeUpdate();
		        		if (rowsInserted > 0) {
		        		    System.out.println("A new data was inserted successfully into database!");
		        		}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}       
	    		}
	        }
            rd.include(request, response);
	         
	    }

}
