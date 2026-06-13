package com.travel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BookingDAO {

    public boolean bookPackage(int userId,
                               int packageId) {

        boolean status = false;

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(
                            "INSERT INTO bookings(user_id,package_id,booking_date) VALUES(?,?,CURDATE())");

            ps.setInt(1, userId);
            ps.setInt(2, packageId);

            int i = ps.executeUpdate();

            if (i > 0)
                status = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}