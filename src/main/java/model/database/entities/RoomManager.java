package model.database.entities;

import model.database.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomManager {

    private final Connection connection;

    public RoomManager() {
        this.connection = JDBC.getConnection();
    }

    // Create a new room
    public void createRoom(Room room) throws SQLException {
        String query = "INSERT INTO rooms (room_id, room_name, availability, capacity, price, room_type) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, room.getId());
            stmt.setString(2, room.getName());
            stmt.setBoolean(3, room.isAvailability());
            stmt.setInt(4, room.getCapacity());
            stmt.setFloat(5, room.getPrice());
            stmt.setString(6, room.getRoomType());
            stmt.executeUpdate();
        }
    }

    // Read room details by room_id
    public Room getRoomById(String roomId) throws SQLException {
        String query = "SELECT * FROM rooms WHERE room_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, roomId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Room(
                        rs.getString("room_id"),
                        rs.getString("room_name"),
                        rs.getBoolean("availability"),
                        rs.getInt("capacity"),
                        rs.getFloat("price"),
                        rs.getString("room_type")
                );
            }
        }
        return null;
    }

    // Get available rooms by date
    public List<Room> getAvailableRoomsByDate(Date date) throws SQLException {
        List<Room> availableRooms = new ArrayList<>();
        String query = "SELECT * FROM rooms r WHERE r.available = TRUE AND NOT EXISTS (" +
                "SELECT 1 FROM bookings b WHERE b.room_id = r.room_id AND b.start_date <= ? AND b.end_date >= ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, date);
            stmt.setDate(2, date);

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    String roomId = resultSet.getString("room_id");
                    String roomName = resultSet.getString("room_name");
                    boolean availability = resultSet.getBoolean("availability");
                    int capacity = resultSet.getInt("capacity");
                    float price = resultSet.getFloat("price");
                    String roomType = resultSet.getString("room_type");
                    Room room = new Room(roomId, roomName, availability, capacity, price, roomType);
                    availableRooms.add(room);
                }
            }
        }
        return availableRooms;
    }

    // Update room details (e.g., name, availability, price)
    public void updateRoom(Room room) throws SQLException {
        String query = "UPDATE rooms SET room_name = ?, availability = ?, capacity = ?, price = ?, room_type = ? WHERE room_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, room.getName());
            stmt.setBoolean(2, room.isAvailability());
            stmt.setInt(3, room.getCapacity());
            stmt.setFloat(4, room.getPrice());
            stmt.setString(5, room.getRoomType());
            stmt.setString(6, room.getId());
            stmt.executeUpdate();
        }
    }

    // Delete room by room_id
    public void deleteRoom(String roomId) throws SQLException {
        String query = "DELETE FROM rooms WHERE room_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, roomId);
            stmt.executeUpdate();
        }
    }
}