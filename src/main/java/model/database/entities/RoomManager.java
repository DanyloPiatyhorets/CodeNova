package model.database.entities;

import model.database.JDBC;

import java.sql.*;

public class RoomManager {

    private final Connection connection;

    public RoomManager() {
        this.connection = JDBC.getConnection();
    }

    // Create a new room
    public void createRoom(Room room) throws SQLException {
        String query = "INSERT INTO rooms (room_name, classroom_capacity, boardroom_capacity, presentation_capacity, "
                + "hourly_rate, morning_afternoon_rate, all_day_rate, weekly_rate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Set values from the Room object
            stmt.setString(1, room.getName());
            stmt.setInt(2, room.getClassroomCapacity());
            stmt.setInt(3, room.getBoardroomCapacity());
            stmt.setInt(4, room.getPresentationCapacity());
            stmt.setInt(5, room.getRateOneHour());
            stmt.setInt(6, room.getRateMorningAfternoon());
            stmt.setInt(7, room.getRateAllDay());
            stmt.setInt(8, room.getRateWeek());

            // Execute the insert statement
            stmt.executeUpdate();
        }
    }

    // Get room the database by room_id
    public Room getRoomByName(String name) throws SQLException {
        String query = "SELECT * FROM rooms WHERE room_name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Extract data from the ResultSet and create a Room object
                    int classroomCapacity = rs.getInt("classroom_capacity");
                    int boardroomCapacity = rs.getInt("boardroom_capacity");
                    int presentationCapacity = rs.getInt("presentation_capacity");
                    int rateOneHour = rs.getInt("hourly_rate");
                    int rateMorningAfternoon = rs.getInt("morning_afternoon_rate");
                    int rateAllDay = rs.getInt("all_day_rate");
                    int rateWeek = rs.getInt("weekly_rate");

                    // Create and return a Room object with the retrieved data
                    return new Room(name, classroomCapacity, boardroomCapacity, presentationCapacity,
                            rateOneHour, rateMorningAfternoon, rateAllDay, rateWeek);
                }
            }
        }
        return null;  // Return null if no room was found with the provided ID
    }

    // Update room details
    public void updateRoom(Room room) throws SQLException {
        String query = "UPDATE rooms SET classroom_capacity = ?, boardroom_capacity = ?, presentation_capacity = ?, " +
                "hourly_rate = ?, morning_afternoon_rate = ?, all_day_rate = ?, weekly_rate = ? WHERE room_name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, room.getClassroomCapacity());
            stmt.setInt(2, room.getBoardroomCapacity());
            stmt.setInt(3, room.getPresentationCapacity());
            stmt.setInt(4, room.getRateOneHour());
            stmt.setInt(5, room.getRateMorningAfternoon());
            stmt.setInt(6, room.getRateAllDay());
            stmt.setInt(7, room.getRateWeek());
            stmt.setString(8, room.getName());  // used in WHERE clause
            stmt.executeUpdate();
        }
    }

    // Delete room by room_id
    public void deleteRoom(String room_name) throws SQLException {
        String query = "DELETE FROM rooms WHERE room_name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, room_name);
            stmt.executeUpdate();
        }
    }

    // Get available rooms by date
    // TODO: has to be refacored because we retrieve this logic from booking_room table
//    public List<Room> getAvailableRoomsByDate(Date date) throws SQLException {
//        List<Room> availableRooms = new ArrayList<>();
//        String query = "SELECT * FROM rooms r WHERE r.available = TRUE AND NOT EXISTS (" +
//                "SELECT 1 FROM bookings b WHERE b.room_id = r.room_id AND b.start_date <= ? AND b.end_date >= ?)";
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setDate(1, date);
//            stmt.setDate(2, date);
//
//            try (ResultSet resultSet = stmt.executeQuery()) {
//                while (resultSet.next()) {
//                    String roomId = resultSet.getString("room_id");
//                    String roomName = resultSet.getString("room_name");
//                    boolean availability = resultSet.getBoolean("availability");
//                    int capacity = resultSet.getInt("capacity");
//                    float price = resultSet.getFloat("price");
//                    String roomType = resultSet.getString("room_type");
//                    Room room = new Room(roomId, roomName, availability, capacity, price, roomType);
//                    availableRooms.add(room);
//                }
//            }
//        }
//        return availableRooms;
//    }


}