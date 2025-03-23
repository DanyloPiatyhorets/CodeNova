package model.database.entities;

public class Room {
     private String id;
     private String name;
     private boolean availability;
     private int capacity;

     public Room(String id, String name, boolean availability, int capacity) {
          this.id = id;
          this.name = name;
          this.availability = availability;
          this.capacity = capacity;
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
}
