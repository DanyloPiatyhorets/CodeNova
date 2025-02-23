package api;

import java.util.Date;
import java.util.List;



public interface MarketingSystemInterface {
    // Calendar Access
    void readCalendar();
    void writeCalendarEvent(int eventId, Date eventDate, String startTime, int duration, String venueName, String roomStatus, String roomName, Date eventEndDate);

    // Venue Holds
    void placeHold(int groupSize, String venueName);
    void notifyRelevantTeams(int holdId);
    void releaseHold(int holdId);
    void expireHold(int holdId);

    // Seating Arrangements Data
    void fetchSeatingData();
    void updateSeatingArrangement(List<String> seatNumbers, List<String> seatStatuses, List<String> rowNumbers, List<Boolean> accessibilityFriendly);

    // Event Attendance Data
    void fetchEventAttendanceData(int eventId);
}