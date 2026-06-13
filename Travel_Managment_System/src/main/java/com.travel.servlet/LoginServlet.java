package com.travel.servlet;

import com.travel.dao.UserDAO;
import com.travel.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        UserDAO userDAO = new UserDAO();
        User user = userDAO.loginUser(username, password);
        
        if (user != null) {
            // Create session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            
            // Redirect based on role
            if (user.getRole().equals("admin")) {
                response.sendRedirect("admin_dashboard.jsp");
            } else {
                response.sendRedirect("dashboard.jsp");
            }
        } else {
            request.setAttribute("message", "Invalid username or password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
