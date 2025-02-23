package entities;

import java.time.LocalDateTime;

public class SmallBooking extends Booking {
    public SmallBooking(String bookingId, Customer customer, LocalDateTime startDate, LocalDateTime endDate) {
        super(bookingId, customer, startDate, endDate);
    }
}
