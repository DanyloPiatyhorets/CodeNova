package model.database.entities;

public class Contract {
    private Customer customer;
    private Event event;
    private String details;

    public Contract(Customer customer, Event event, String details) {
        this.customer = customer;
        this.event = event;
        this.details = details;
    }
}
