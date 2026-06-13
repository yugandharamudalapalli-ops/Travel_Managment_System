package com.travel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.UserDAO;
import com.travel.model.User;

public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFullName(fullName);
        user.setRole("USER");

        UserDAO dao = new UserDAO();

        boolean status = dao.register(user);

        if (status) {
            response.sendRedirect("login.jsp?msg=registered");
        } else {
            response.sendRedirect("register.jsp?msg=failed");
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
}