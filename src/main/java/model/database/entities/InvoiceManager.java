package model.database.entities;

import model.database.JDBC;
import model.database.entities.Invoice;

import java.sql.*;
import java.time.LocalDateTime;

public class InvoiceManager {

    private final Connection connection;

    public InvoiceManager() {
        this.connection = JDBC.getConnection();
    }

    // Create a new invoice
    public void createInvoice(Invoice invoice) throws SQLException {
        String query = "INSERT INTO invoices (booking_id, client_name, date, amount_due, payment_status, payment_date, invoice_details) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, invoice.getBookingId());
            stmt.setString(2, invoice.getClientName());
            stmt.setTimestamp(3, Timestamp.valueOf(invoice.getDate()));  // Convert LocalDateTime to Timestamp
            stmt.setDouble(4, invoice.getAmountDue());
            stmt.setString(5, invoice.getPaymentStatus());
            if (invoice.getPaymentDate() != null) {
                stmt.setTimestamp(6, Timestamp.valueOf(invoice.getPaymentDate()));
            } else {
                stmt.setNull(6, Types.TIMESTAMP);
            }
            stmt.setString(7, invoice.getInvoiceDetails());
            stmt.executeUpdate();
        }
    }

    // Read invoice by invoice_id
    public Invoice getInvoiceById(int invoiceId) throws SQLException {
        String query = "SELECT * FROM invoices WHERE invoice_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, invoiceId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Invoice(
                        rs.getInt("invoice_id"),
                        rs.getString("booking_id"),
                        rs.getString("client_name"),
                        rs.getTimestamp("date").toLocalDateTime(),
                        rs.getDouble("amount_due"),
                        rs.getString("payment_status"),
                        rs.getTimestamp("payment_date") != null ? rs.getTimestamp("payment_date").toLocalDateTime() : null,
                        rs.getString("invoice_details")
                );
            }
        }
        return null;
    }

    // Update invoice status by invoice_id
    public void updateInvoiceStatus(int invoiceId, String paymentStatus, LocalDateTime paymentDate) throws SQLException {
        String query = "UPDATE invoices SET payment_status = ?, payment_date = ? WHERE invoice_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, paymentStatus);
            stmt.setTimestamp(2, paymentDate != null ? Timestamp.valueOf(paymentDate) : null);
            stmt.setInt(3, invoiceId);
            stmt.executeUpdate();
        }
    }

    // Delete invoice by invoice_id
    public void deleteInvoice(int invoiceId) throws SQLException {
        String query = "DELETE FROM invoices WHERE invoice_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, invoiceId);
            stmt.executeUpdate();
        }
    }
}
