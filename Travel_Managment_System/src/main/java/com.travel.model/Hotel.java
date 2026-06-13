package com.travel.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.travel.model.Hotel;

public class Hotel {

    // Add Hotel
    public boolean addHotel(Hotel hotel) {

        boolean status = false;

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO hotels(hotel_name, location, rating, price_per_night) VALUES(?,?,?,?)");

            ps.setString(1, hotel.getHotelName());
            ps.setString(2, hotel.getLocation());
            ps.setDouble(3, hotel.getRating());
            ps.setDouble(4, hotel.getPricePerNight());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Get All Hotels
    public List<Hotel> getAllHotels() {

        List<Hotel> hotelList = new ArrayList<>();

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement("SELECT * FROM hotels");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Hotel hotel = new Hotel();

                hotel.setHotelId(rs.getInt("hotel_id"));
                hotel.setHotelName(rs.getString("hotel_name"));
                hotel.setLocation(rs.getString("location"));
                hotel.setRating(rs.getDouble("rating"));
                hotel.setPricePerNight(rs.getDouble("price_per_night"));

                hotelList.add(hotel);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hotelList;
    }

    // Get Hotel By ID
    public Hotel getHotelById(int hotelId) {

        Hotel hotel = null;

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(
                    "SELECT * FROM hotels WHERE hotel_id=?");

            ps.setInt(1, hotelId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                hotel = new Hotel();

                hotel.setHotelId(rs.getInt("hotel_id"));
                hotel.setHotelName(rs.getString("hotel_name"));
                hotel.setLocation(rs.getString("location"));
                hotel.setRating(rs.getDouble("rating"));
                hotel.setPricePerNight(rs.getDouble("price_per_night"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hotel;
    }

    // Update Hotel
    public boolean updateHotel(Hotel hotel) {

        boolean status = false;

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(
                "UPDATE hotels SET hotel_name=?, location=?, rating=?, price_per_night=? WHERE hotel_id=?");

            ps.setString(1, hotel.getHotelName());
            ps.setString(2, hotel.getLocation());
            ps.setDouble(3, hotel.getRating());
            ps.setDouble(4, hotel.getPricePerNight());
            ps.setInt(5, hotel.getHotelId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // Delete Hotel
    public boolean deleteHotel(int hotelId) {

        boolean status = false;

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(
                    "DELETE FROM hotels WHERE hotel_id=?");

            ps.setInt(1, hotelId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}