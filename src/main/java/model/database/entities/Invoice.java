package model.database.entities;

import java.time.LocalDateTime;

public class Invoice {
    private int invoiceId;
    private String bookingId;
    private String clientName;
    private LocalDateTime date;
    private double amountDue;
    private String paymentStatus;
    private LocalDateTime paymentDate;
    private String invoiceDetails;

    // Constructor
    public Invoice(int invoiceId, String bookingId, String clientName, LocalDateTime date,
                   double amountDue, String paymentStatus, LocalDateTime paymentDate, String invoiceDetails) {
        this.invoiceId = invoiceId;
        this.bookingId = bookingId;
        this.clientName = clientName;
        this.date = date;
        this.amountDue = amountDue;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.invoiceDetails = invoiceDetails;
    }

    // Getters and Setters
    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(String invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }
}
