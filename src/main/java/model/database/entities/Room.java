package model.database.entities;

import model.database.JDBC;

import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;


public class Room {
     private String id;
     private String name;
     private boolean availability;
     private int capacity;
     private float price;
     private String roomType;
     private final Connection connection;


     //this class is to create rooms
     public Room(String id, String name, boolean availability, int capacity,float price, String roomType) {
          this.id = id;
          this.name = name;
          this.availability = availability;
          this.capacity = capacity;
          this.price = price;
          this.roomType = roomType;
          this.connection = JDBC.getConnection();
     }

     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public boolean isAvailability() {
          return availability;
     }

     public void setAvailability(boolean availability) {
          this.availability = availability;
     }

     public int getCapacity() {
          return capacity;
     }

     public void setCapacity(int capacity) {




          this.capacity = capacity;
     }

     public float getPrice() {
          return price;
     }

     public void setPrice(float price) {
          this.price = price;
     }

     public String getRoomType() {
          return roomType;
     }

     public void setRoomType(String roomType) {



          this.roomType = roomType;
     }


     public List<Room> getAvailableRoomsByDate(Date date) {
          List<Room> availableRooms = new ArrayList<>();

          String query = "SELECT room_id, room_name, availability, capacity, price, room_type " +
                  "FROM rooms r " +
                  "WHERE r.available = TRUE " +  // Ensure the room is available
                  "AND NOT EXISTS ( " +
                  "    SELECT 1 FROM bookings b " +
                  "    WHERE b.room_id = r.room_id " +
                  "    AND b.start_date <= ? " +  // The room's booking start date must be after the provided date
                  "    AND b.end_date >= ? " +    // The room's booking end date must be before the provided date
                  ")";
          try (PreparedStatement stmt = connection.prepareStatement(query)) {
               stmt.setDate(1, date);  // Setting the date for both check-in and check-out
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
          } catch (SQLException e) {
               e.printStackTrace();
          }

          return availableRooms;
     }

}
