package api;

import entities.Booking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



interface BoxOfficeSystemInterface {
    // Calendar Access
    void readCalendar();
    // Seating Arrangements Data
    void fetchSeatingData();
    void updateSeatingArrangement(List<String> seatNumbers, List<String> seatStatuses, List<String> rowNumbers, List<Boolean> accessibilityFriendly);
    // Event Attendance Data
    void fetchEventAttendanceData(int eventId);
    //Book seat for customer
    void createCustomerBooking(String customerName, int seatNumber, boolean accessibilityRequired);
    //Verify seat data is correct
    void verifySeatData(int seatBookingID);
    // Provide list of changes in calendar made by Operations/Marketing
    void calendarUpdates();
    //send alert to box office if change is made
    void sendAlert();
}