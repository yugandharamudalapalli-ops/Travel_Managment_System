package model;

public class Booking {

}
package com.travel.model;

public class Booking {

    private int bookingId;
    private int userId;
    private int packageId;
    private String bookingDate;

    
    public Booking() {
    }

    
    public Booking(int bookingId, int userId, int packageId, String bookingDate) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.packageId = packageId;
        this.bookingDate = bookingDate;
    }

    // Getters and Setters

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId +
               ", userId=" + userId +
               ", packageId=" + packageId +
               ", bookingDate=" + bookingDate + "]";
    }
}