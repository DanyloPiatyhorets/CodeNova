package model.database.entities;

import java.time.LocalDateTime;

public class Invoice {
    private LocalDateTime date;
    private int total;



    public Invoice(LocalDateTime date, int total) {
        this.date = date;
        this.total = total;

    }






}

