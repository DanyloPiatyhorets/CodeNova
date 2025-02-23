package entities;

import java.time.LocalDateTime;


public abstract class Booking {

    //the unique ID that is given to each booking
    protected String bookingId;
    //the bookings are under one name
    protected Customer customer;
    //the runs have a start date
    protected LocalDateTime startDate;
    //the runs have an end date
    protected LocalDateTime endDate;
    //the booking has to be confirmed within 28 days!!"!!!
    protected static boolean confirmed = false;


    public Booking(String bookingId, Customer customer, LocalDateTime startDate, LocalDateTime endDate) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    static boolean confrimBooking(){
        return confirmed = true;

    }
}