package model.database.entities.booking;

import model.database.entities.Customer;

import java.time.LocalDateTime;

public class LargeBooking extends Booking {
    public LargeBooking(String id, Customer customer, LocalDateTime startDate, LocalDateTime endDate, boolean confirmed) {
        super(id, customer, startDate, endDate, confirmed);
    }
}
