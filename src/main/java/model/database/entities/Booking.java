package model.database.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/// Booking has many-to-many relationship with Room
public class Booking {
    private int bookingId;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String details;
    private boolean confirmed;

    // One booking can have many rooms booked
    private List<Room> rooms;

    public Booking(LocalDate date, LocalTime startTime, LocalTime endTime, String details, boolean confirmed, List<Room> rooms) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.details = details;
        this.confirmed = confirmed;
        this.rooms = rooms;
    }
    public Booking(int id, LocalDate date, LocalTime startTime, LocalTime endTime, String details, boolean confirmed, List<Room> rooms) {
        this.bookingId = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.details = details;
        this.confirmed = confirmed;
        this.rooms = rooms;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void addRoom(Room room){
        rooms.add(room);
    };
    public void removeRoom(Room room){
        rooms.remove(room);
    }
    public List<Room> getRooms(){
        return rooms;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
