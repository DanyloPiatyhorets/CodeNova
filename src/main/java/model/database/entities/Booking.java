package model.database.entities;

import java.time.LocalDateTime;

public class Booking {
    //the unique ID that is given to each booking
    protected String id;
    //the bookings are under one name
    protected Customer customer;
    //the runs have a start date
    protected LocalDateTime startDate;
    //the runs have an end date
    protected LocalDateTime endDate;
    //the booking has to be confirmed within 28 days!!"!!!
    protected boolean confirmed;


    public Booking(String id, Customer customer, LocalDateTime startDate, LocalDateTime endDate, boolean confirmed) {
        this.id = id;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.confirmed = confirmed;
    }
}
