package com.travel.servlet;

import com.travel.dao.BookingDAO;
import com.travel.model.Booking;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.math.BigDecimal;

public class BookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        int packageId = Integer.parseInt(request.getParameter("packageId"));
        String travelDate = request.getParameter("travelDate");
        int numPeople = Integer.parseInt(request.getParameter("numPeople"));
        
        BookingDAO bookingDAO = new BookingDAO();
        Booking booking = new Booking();
        booking.setUserId(user.getUserId());
        booking.setPackageId(packageId);
        booking.setTravelDate(travelDate);
        booking.setNumPeople(numPeople);
        
        boolean success = bookingDAO.createBooking(booking);
        
        if (success) {
            response.sendRedirect("booking_success.jsp");
        } else {
            request.setAttribute("message", "Booking failed!");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
        }
    }
}
