package api;

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
    void createCustomerBooking(String customerName, int seatNumber, boolean accessibilityRequired);
}