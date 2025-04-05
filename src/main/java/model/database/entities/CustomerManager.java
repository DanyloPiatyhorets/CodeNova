package model.database.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerManager {

private final Connection connection;

    public CustomerManager(Connection connection) {
        this.connection = connection;
    }

    // Create a new customer (client in the database)
    public void createCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO clients (name, phone_number, email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getPhoneNumber());
            stmt.setString(3, customer.getEmail());
            stmt.executeUpdate();
        }
    }

    // Read customer by name (you can modify this to use other identifiers like phone number or email)
    public Customer getCustomerByName(String name) throws SQLException {
        String query = "SELECT * FROM clients WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getString("name"),
                        rs.getString("phone_number"),
                        rs.getString("email")
                );
            }
        }
        return null;
    }

    // Update customer details by name
    public void updateCustomer(Customer customer) throws SQLException {
        String query = "UPDATE clients SET phone_number = ?, email = ? WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, customer.getPhoneNumber());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete customer by name (you can modify to use other identifiers like email or phone number)
    public void deleteCustomer(String name) throws SQLException {
        String query = "DELETE FROM clients WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
    }
}





