package com.test;

import com.test.JDBCConnector.ConnectionImplementation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    Connection connection;
    PreparedStatement preparedStatement;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out =resp.getWriter();
        String username= req.getParameter("username");
        String contact= req.getParameter("contact");
        ServletContext application = getServletContext();
        application.setAttribute("username",username);
        application.setAttribute("contact",contact);
        if(ValidationServlet.checkAlphabate(username)){
           try {
               connection = ConnectionImplementation.getConnectionForDB();
               preparedStatement = connection.prepareStatement("INSERT INTO REGISTRATION VALUES(?,?,?,?,?)");
               preparedStatement.setString(1,req.getParameter("username"));
               preparedStatement.setString(2,req.getParameter("password'"));
               preparedStatement.setInt(3,Integer.parseInt(req.getParameter("contact")));
               preparedStatement.setString(4,req.getParameter("city"));
               preparedStatement.setString(5,req.getParameter("education"));
               if(preparedStatement.executeUpdate()!=0) {
                   out.println("Registration Successful !!");
                   out.println(application.getAttribute("username"));
                   RequestDispatcher dispatcher = req.getRequestDispatcher("LoginPage.html");
                   dispatcher.forward(req,resp);
               }
               else out.println("Something Went Wrong !!");
           }catch (SQLException e){
               System.out.println(e.getMessage());
           }
        }
        else{
            out.println("Please Enter valid details ");
            RequestDispatcher dispatcher = req.getRequestDispatcher("Form.html");
            dispatcher.include(req,resp);
        }

    }
}
