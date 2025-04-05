package model.database.entities;

import model.database.JDBC;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BookingManager {

    private final Connection connection;

    public BookingManager() {
        this.connection = JDBC.getConnection();
    }

    // Create a new room
    public int createBooking(Booking booking) throws SQLException {
        // Step 1: Insert the Booking record and get the generated ID (UUID)
        String bookingQuery = "INSERT INTO bookings (date, start_time, end_time, details, confirmed) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(bookingQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, Date.valueOf(booking.getDate()));
            stmt.setTime(2, Time.valueOf(booking.getStartTime()));
            stmt.setTime(3, Time.valueOf(booking.getEndTime()));
            stmt.setString(4, booking.getDetails());
            stmt.setBoolean(5, booking.isConfirmed());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int generatedBookingId = rs.getInt(1);
                    booking.setBookingId(generatedBookingId);
                }
            }
        }

        // Step 2: Insert entries into the rooms_booking table
        String roomsBookingQuery = "INSERT INTO booking_room (booking_id, room_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(roomsBookingQuery)) {
            for (Room room : booking.getRooms()) {
                stmt.setInt(1, booking.getBookingId()); // The generated booking ID
                stmt.setInt(2, room.getRoomId()); // Room's ID from the Room object
                stmt.addBatch();  // Add each room to the batch
            }
            stmt.executeBatch();
        }
        return booking.getBookingId();

    }

    public Booking getBookingById(int bookingId) throws SQLException {
        String bookingQuery = "SELECT * FROM bookings WHERE booking_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(bookingQuery)) {
            stmt.setInt(1, bookingId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Extract booking data
                    LocalDate date = rs.getDate("date").toLocalDate();
                    LocalTime startTime = rs.getTime("start_time").toLocalTime();
                    LocalTime endTime = rs.getTime("end_time").toLocalTime();
                    String details = rs.getString("details");
                    boolean confirmed = rs.getBoolean("confirmed");

                    // Get rooms associated with this booking
                    List<Room> rooms = getRoomsForBooking(bookingId);

                    // Create and return Booking object
                    return new Booking(date, startTime, endTime, details, confirmed, rooms);
                }
            }
        }
        return null; // Return null if no booking found with the given ID
    }

    private List<Room> getRoomsForBooking(int bookingId) throws SQLException {
        String roomsQuery = "SELECT * FROM rooms r " +
                "JOIN booking_room br ON r.room_id = br.room_id WHERE br.booking_id = ?";
        List<Room> rooms = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(roomsQuery)) {
            stmt.setInt(1, bookingId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("room_id");
                    String name = rs.getString("room_name");
                    int classroomCapacity = rs.getInt("classroom_capacity");
                    int boardroomCapacity = rs.getInt("boardroom_capacity");
                    int presentationCapacity = rs.getInt("presentation_capacity");
                    int rateOneHour = rs.getInt("hourly_rate");
                    int rateMorningAfternoon = rs.getInt("morning_afternoon_rate");
                    int rateAllDay = rs.getInt("all_day_rate");
                    int rateWeek = rs.getInt("weekly_rate");

                    // Create and return a Room object with the retrieved data
                    rooms.add(new Room(id, name, classroomCapacity, boardroomCapacity, presentationCapacity,
                            rateOneHour, rateMorningAfternoon, rateAllDay, rateWeek));
                }
            }
        }
        return rooms;
    }


    // Update room details
    public void updateBooking(Booking booking) throws SQLException {
        String bookingQuery = "UPDATE bookings SET date = ?, start_time = ?, end_time = ?, details = ?, confirmed = ? WHERE booking_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(bookingQuery)) {
            stmt.setDate(1, Date.valueOf(booking.getDate()));
            stmt.setTime(2, Time.valueOf(booking.getStartTime()));
            stmt.setTime(3, Time.valueOf(booking.getEndTime()));
            stmt.setString(4, booking.getDetails());
            stmt.setBoolean(5, booking.isConfirmed());
            stmt.setInt(6, booking.getBookingId());

            stmt.executeUpdate();
        }

        // Step 2: Update the booking_room table (add or remove rooms)
        // First, delete old room associations
        String deleteRoomsQuery = "DELETE FROM booking_room WHERE booking_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteRoomsQuery)) {
            stmt.setInt(1, booking.getBookingId());
            stmt.executeUpdate();
        }

        // Step 3: Add new room associations
        String insertRoomsQuery = "INSERT INTO booking_room (booking_id, room_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(insertRoomsQuery)) {
            for (Room room : booking.getRooms()) {
                stmt.setInt(1, booking.getBookingId());
                stmt.setInt(2, room.getRoomId());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

//    // Delete room by room_id
public void deleteBooking(int bookingId) throws SQLException {
    // Step 1: Delete room associations in the booking_room table
    String deleteRoomsQuery = "DELETE FROM booking_room WHERE booking_id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(deleteRoomsQuery)) {
        stmt.setInt(1, bookingId);
        stmt.executeUpdate();
    }

    // Step 2: Delete the booking itself from the bookings table
    String bookingQuery = "DELETE FROM bookings WHERE booking_id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(bookingQuery)) {
        stmt.setInt(1, bookingId);
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
