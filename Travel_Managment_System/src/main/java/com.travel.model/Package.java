package com.travel.model;

public class Hotel {

    private int hotelId;
    private String hotelName;
    private String location;
    private double rating;
    private double pricePerNight;

    // Default Constructor
    public Hotel() {
    }

    // Parameterized Constructor
    public Hotel(int hotelId, String hotelName,
                 String location, double rating,
                 double pricePerNight) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.location = location;
        this.rating = rating;
        this.pricePerNight = pricePerNight;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Override
    public String toString() {
        return "Hotel [hotelId=" + hotelId
                + ", hotelName=" + hotelName
                + ", location=" + location
                + ", rating=" + rating
                + ", pricePerNight=" + pricePerNight + "]";
    }
}