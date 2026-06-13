package com.travel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.dao.PackageDAO;
import com.travel.model.TravelPackage;

public class PackageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        PackageDAO dao = new PackageDAO();

        try {

            if ("add".equals(action)) {

                TravelPackage p = new TravelPackage();

                p.setPackageName(request.getParameter("packageName"));
                p.setDestination(request.getParameter("destination"));
                p.setDuration(Integer.parseInt(request.getParameter("duration")));
                p.setPrice(Double.parseDouble(request.getParameter("price")));

                if (dao.addPackage(p)) {
                    response.sendRedirect("packages.jsp?msg=added");
                } else {
                    response.sendRedirect("packages.jsp?msg=failed");
                }

            } else if ("delete".equals(action)) {

                int packageId =
                        Integer.parseInt(request.getParameter("packageId"));

                if (dao.deletePackage(packageId)) {
                    response.sendRedirect("packages.jsp?msg=deleted");
                } else {
                    response.sendRedirect("packages.jsp?msg=failed");
                }

            } else if ("update".equals(action)) {

                TravelPackage p = new TravelPackage();

                p.setPackageId(
                        Integer.parseInt(request.getParameter("packageId")));
                p.setPackageName(request.getParameter("packageName"));
                p.setDestination(request.getParameter("destination"));
                p.setDuration(
                        Integer.parseInt(request.getParameter("duration")));
                p.setPrice(
                        Double.parseDouble(request.getParameter("price")));

                if (dao.updatePackage(p)) {
                    response.sendRedirect("packages.jsp?msg=updated");
                } else {
                    response.sendRedirect("packages.jsp?msg=failed");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("packages.jsp?msg=error");
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
}