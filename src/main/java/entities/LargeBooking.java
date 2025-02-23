package entities;

import java.time.LocalDateTime;

public class LargeBooking extends Booking {
    public LargeBooking(String bookingId, Customer customer, LocalDateTime startDate, LocalDateTime endDate) {
        super(bookingId, customer, startDate, endDate);
    }
}
