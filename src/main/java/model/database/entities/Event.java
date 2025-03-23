package model.database.entities;

import java.time.LocalDateTime;

public class Event {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int maxDiscount;
    private String description;

    public Event(String name, LocalDateTime startDate, LocalDateTime endDate, int maxDiscount, String description) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxDiscount = maxDiscount;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(int maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
