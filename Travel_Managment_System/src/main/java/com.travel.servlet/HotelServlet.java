package com.travel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.HotelDAO;
import com.travel.model.Hotel;

public class HotelServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        HotelDAO dao = new HotelDAO();

        try {

            if ("add".equals(action)) {

                Hotel hotel = new Hotel();

                hotel.setHotelName(
                        request.getParameter("hotelName"));

                hotel.setLocation(
                        request.getParameter("location"));

                hotel.setRating(
                        Double.parseDouble(
                                request.getParameter("rating")));

                hotel.setPricePerNight(
                        Double.parseDouble(
                                request.getParameter("pricePerNight")));

                if (dao.addHotel(hotel)) {
                    response.sendRedirect("hotels.jsp?msg=added");
                } else {
                    response.sendRedirect("hotels.jsp?msg=failed");
                }

            } else if ("update".equals(action)) {

                Hotel hotel = new Hotel();

                hotel.setHotelId(
                        Integer.parseInt(
                                request.getParameter("hotelId")));

                hotel.setHotelName(
                        request.getParameter("hotelName"));

                hotel.setLocation(
                        request.getParameter("location"));

                hotel.setRating(
                        Double.parseDouble(
                                request.getParameter("rating")));

                hotel.setPricePerNight(
                        Double.parseDouble(
                                request.getParameter("pricePerNight")));

                if (dao.updateHotel(hotel)) {
                    response.sendRedirect("hotels.jsp?msg=updated");
                } else {
                    response.sendRedirect("hotels.jsp?msg=failed");
                }

            } else if ("delete".equals(action)) {

                int hotelId = Integer.parseInt(
                        request.getParameter("hotelId"));

                if (dao.deleteHotel(hotelId)) {
                    response.sendRedirect("hotels.jsp?msg=deleted");
                } else {
                    response.sendRedirect("hotels.jsp?msg=failed");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("hotels.jsp?msg=error");
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
}