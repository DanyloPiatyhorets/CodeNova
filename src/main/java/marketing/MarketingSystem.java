package marketing;

import api.MarketingSystemInterface;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public abstract class MarketingSystem implements MarketingSystemInterface {
    public List<String> availableSeats, seatNumbers, rowNumbers, seatStatuses;
    public List<Boolean> accessibilityFriendly;

    public int eventId, roomId, totalTicketsSold, ticketsUsed;
    public String eventName, startTime, endTime, ticketType;
    public Date eventDate;
    // Calendar Access
    public abstract void readCalendar();
    public abstract void writeCalendarEvent(int eventId, Date eventDate, String startTime, int duration, String venueName, String roomStatus, String roomName, Date eventEndDate);
    // Venue Holds
    public abstract void placeHold(int groupSize, String venueName);
    public abstract void notifyRelevantTeams(int holdId);
    public abstract void releaseHold(int holdId);
    public abstract void expireHold(int holdId);

    // Seating Arrangements Data
    public abstract void fetchSeatingData();
    public abstract void updateSeatingArrangement(List<String> seatNumbers, List<String> seatStatuses);

    // Event Attendance Data
    public abstract void fetchEventAttendanceData(int eventId);
}

