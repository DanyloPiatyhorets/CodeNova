package model.database.entities;

import java.util.List;

/// Room has many-to-many relationship with Booking
public class Room {
     private String roomId;

     private String name;
     private int classroomCapacity;
     private int boardroomCapacity;
     private int presentationCapacity;

     private int rateOneHour;
     private int rateMorningAfternoon;
     private int rateAllDay;
     private int rateWeek;

     // One room can have many bookings at different times
     private List<Booking> bookings;

     public Room(
                 String name,
                 int classroomCapacity,
                 int boardroomCapacity,
                 int presentationCapacity,
                 int rateOneHour,
                 int rateMorningAfternoon,
                 int rateAllDay,
                 int rateWeek) {
          this.name = name;
          this.classroomCapacity = classroomCapacity;
          this.boardroomCapacity = boardroomCapacity;
          this.presentationCapacity = presentationCapacity;
          this.rateOneHour = rateOneHour;
          this.rateMorningAfternoon = rateMorningAfternoon;
          this.rateAllDay = rateAllDay;
          this.rateWeek = rateWeek;
     }

     public void addBooking(Booking booking){
          bookings.add(booking);
     };
     public void removeBooking(Booking booking){
          bookings.remove(booking);
     }
     public List<Booking> getBookings(){
          return bookings;
     }

     public String getRoomId() {
          return roomId;
     }

     public void setRoomId(String roomId) {
          this.roomId = roomId;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public int getClassroomCapacity() {
          return classroomCapacity;
     }

     public void setClassroomCapacity(int classroomCapacity) {
          this.classroomCapacity = classroomCapacity;
     }

     public int getBoardroomCapacity() {
          return boardroomCapacity;
     }

     public void setBoardroomCapacity(int boardroomCapacity) {
          this.boardroomCapacity = boardroomCapacity;
     }

     public int getPresentationCapacity() {
          return presentationCapacity;
     }

     public void setPresentationCapacity(int presentationCapacity) {
          this.presentationCapacity = presentationCapacity;
     }

     public int getRateOneHour() {
          return rateOneHour;
     }

     public void setRateOneHour(int rateOneHour) {
          this.rateOneHour = rateOneHour;
     }

     public int getRateMorningAfternoon() {
          return rateMorningAfternoon;
     }

     public void setRateMorningAfternoon(int rateMorningAfternoon) {
          this.rateMorningAfternoon = rateMorningAfternoon;
     }

     public int getRateAllDay() {
          return rateAllDay;
     }

     public void setRateAllDay(int rateAllDay) {
          this.rateAllDay = rateAllDay;
     }

     public int getRateWeek() {
          return rateWeek;
     }

     public void setRateWeek(int rateWeek) {
          this.rateWeek = rateWeek;
     }
}
