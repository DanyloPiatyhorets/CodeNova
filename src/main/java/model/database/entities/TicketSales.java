package model.database.entities;

import java.time.LocalDateTime;

public class TicketSales {
    private LocalDateTime date;
    private int totalSeats;
    private int value;

    public TicketSales(LocalDateTime date, int totalSeats, int value) {
        this.date = date;
        this.totalSeats = totalSeats;
        this.value = value;
    }
}
