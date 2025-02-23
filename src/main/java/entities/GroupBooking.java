package entities;

import java.time.LocalDateTime;

public abstract class GroupBooking extends Booking {
    public GroupBooking(String bookingId, Customer customer, LocalDateTime startDate, LocalDateTime endDate) {
        super(bookingId, customer, startDate, endDate);
    }
}
