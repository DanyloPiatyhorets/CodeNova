package view;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class MainView extends JFrame {
    private JTabbedPane tabbedPane;

    // Backend service simulation
    private BackendService backend = new BackendService();

    public MainView() {
        super("Lancaster Music Hall - Operations System");
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        createMenuBar();
        createMainTabs();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu reportsMenu = new JMenu("Reports");
        JMenuItem venueUsageItem = new JMenuItem("Venue Usage Report");
        JMenuItem financialItem = new JMenuItem("Financial Summary");
        reportsMenu.add(venueUsageItem);
        reportsMenu.add(financialItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(reportsMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    private void createMainTabs() {
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Calendar", createCalendarPanel());
        tabbedPane.addTab("Bookings", createBookingsPanel());
        tabbedPane.addTab("Seating", createSeatingPanel());
        tabbedPane.addTab("Daily Sheets", createDailySheetsPanel());
        tabbedPane.addTab("Financial Tracking", createFinancialPanel());
        tabbedPane.addTab("Reviews", createReviewsPanel());

        add(tabbedPane);
    }

    private JPanel createCalendarPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        DefaultListModel<String> eventModel = new DefaultListModel<>();


        // Calendar view
        JPanel calendarViewPanel = new JPanel(new BorderLayout());
        JLabel monthLabel = new JLabel("February 2025", JLabel.CENTER);
        monthLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        // Calendar grid
        JPanel calendarGrid = new JPanel(new GridLayout(0, 7));
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : days) {
            calendarGrid.add(new JLabel(day, JLabel.CENTER));
        }

        // Add day cells (simplified for demo)
        for (int i = 1; i <= 28; i++) {
            JButton dayButton = new JButton(String.valueOf(i));
            int finalI = i;
            dayButton.addActionListener(e -> showDayDetails(finalI));
            calendarGrid.add(dayButton);
        }

        calendarViewPanel.add(monthLabel, BorderLayout.NORTH);
        calendarViewPanel.add(calendarGrid, BorderLayout.CENTER);

        // Navigation buttons
        JPanel navPanel = new JPanel();
        JButton prevMonth = new JButton("< Previous");
        JButton nextMonth = new JButton("Next >");
        navPanel.add(prevMonth);
        navPanel.add(nextMonth);
        calendarViewPanel.add(navPanel, BorderLayout.SOUTH);

        // Event list for selected day
        JPanel eventListPanel = new JPanel(new BorderLayout());
        JLabel eventsLabel = new JLabel("Events for Selected Day", JLabel.CENTER);
        JList<String> eventList = new JList<>(eventModel);
        JScrollPane eventScroll = new JScrollPane(eventList);


        eventListPanel.add(eventsLabel, BorderLayout.NORTH);
        eventListPanel.add(eventScroll, BorderLayout.CENTER);

        // Room availability panel
        JPanel roomPanel = new JPanel(new GridLayout(0, 1));
        roomPanel.setBorder(BorderFactory.createTitledBorder("Room Availability"));
        String[] rooms = {"Main Hall", "Small Hall", "Rehearsal Space", "Meeting Room 1",
                "Meeting Room 2", "Meeting Room 3", "Meeting Room 4", "Meeting Room 5"};
        for (String room : rooms) {
            JCheckBox roomCheck = new JCheckBox(room);
            roomPanel.add(roomCheck);
        }

        // Backend integration points
        prevMonth.addActionListener(e -> {
            // TODO: Backend needs to implement getCalendarData(month, year)
            // Sample data until implemented
            updateCalendarGrid(calendarGrid, monthLabel, eventModel);
        });

        nextMonth.addActionListener(e -> {
            // TODO: Backend needs to implement getCalendarData(month, year)
            updateCalendarGrid(calendarGrid, monthLabel, eventModel);
        });




        // Add components to main panel
        panel.add(calendarViewPanel, BorderLayout.CENTER);
        panel.add(eventListPanel, BorderLayout.EAST);
        panel.add(roomPanel, BorderLayout.WEST);
        return panel;
    }

    // Sample data generation for GUI testing
// Temporary implementation with sample data
    private void updateCalendarGrid(JPanel grid, JLabel monthLabel, DefaultListModel<String> events) {
        grid.removeAll();

        // Add day headers
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : days) {
            grid.add(new JLabel(day, JLabel.CENTER));
        }

        // Add sample days
        for (int i = 1; i <= 28; i++) {
            JButton dayBtn = new JButton(String.valueOf(i));
            final int day = i;
            dayBtn.addActionListener(e -> {
                events.clear();
                events.addElement("Performance: Phantom - Main Hall");
                events.addElement("Film: Casablanca - Stalls Only");
                events.addElement("Meeting: Room 3 - 2pm");
            });
            grid.add(dayBtn);
        }

        grid.revalidate();
        grid.repaint();
    }

    private JPanel createBookingsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Booking list
        DefaultListModel<Booking> bookingModel = new DefaultListModel<>();
        backend.getBookings(null, null).forEach(bookingModel::addElement);
        JList<Booking> bookingList = new JList<>(bookingModel);

        // Booking list
        JPanel bookingListPanel = new JPanel(new BorderLayout());
        JLabel bookingLabel = new JLabel("Current Bookings", JLabel.CENTER);
        String[] bookings = {"The Phantom of the Opera - Main Hall - Feb 10-15",
                "Corporate Meeting - Meeting Room 3 - Feb 12",
                "Film: Casablanca - Main Hall - Feb 18"};
        JScrollPane bookingScroll = new JScrollPane(bookingList);

        bookingListPanel.add(bookingLabel, BorderLayout.NORTH);
        bookingListPanel.add(bookingScroll, BorderLayout.CENTER);

        // Booking details
        JPanel detailsPanel = new JPanel(new GridLayout(0, 2));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Booking Details"));

        detailsPanel.add(new JLabel("Client:"));
        detailsPanel.add(new JTextField(20));
        detailsPanel.add(new JLabel("Event Type:"));
        JComboBox<String> eventType = new JComboBox<>(new String[]{"Performance", "Film", "Meeting", "Wedding", "Corporate Event"});
        detailsPanel.add(eventType);
        detailsPanel.add(new JLabel("Start Date:"));
        detailsPanel.add(new JSpinner(new SpinnerDateModel()));
        detailsPanel.add(new JLabel("End Date:"));
        detailsPanel.add(new JSpinner(new SpinnerDateModel()));
        detailsPanel.add(new JLabel("Rooms:"));
        JPanel roomSelection = new JPanel(new GridLayout(0, 1));
        String[] rooms = {"Main Hall", "Small Hall", "Rehearsal Space", "Meeting Rooms"};
        for (String room : rooms) {
            roomSelection.add(new JCheckBox(room));
        }
        detailsPanel.add(roomSelection);
        detailsPanel.add(new JLabel("Configuration:"));
        JComboBox<String> configType = new JComboBox<>(new String[]{"Theater", "Film", "Wedding", "Classroom", "Conference"});
        detailsPanel.add(configType);
        detailsPanel.add(new JLabel("Setup Days Needed:"));
        detailsPanel.add(new JSpinner(new SpinnerNumberModel(1, 0, 3, 1)));
        detailsPanel.add(new JLabel("Revenue Sharing (%):"));
        detailsPanel.add(new JSpinner(new SpinnerNumberModel(0, 0, 100, 5)));

        // Booking actions
        JPanel actionPanel = new JPanel();
        JButton newBooking = new JButton("New Booking");
        JButton saveBooking = new JButton("Save");
        JButton cancelBooking = new JButton("Cancel Booking");
        actionPanel.add(newBooking);
        actionPanel.add(saveBooking);
        actionPanel.add(cancelBooking);

        // Save button handler
        saveBooking.addActionListener(e -> {
            BookingDetails details = new BookingDetails();
            // Populate details from form
            boolean success = backend.saveBooking(details);

            if(success) {
                bookingModel.addElement(new Booking(
                        details.clientName,
                        String.join(",", details.rooms),
                        details.startDate,
                        details.endDate
                ));
            }
        });

        // Add components to main panel
        panel.add(bookingListPanel, BorderLayout.WEST);
        panel.add(detailsPanel, BorderLayout.CENTER);
        panel.add(actionPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createSeatingPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Venue selection
        JPanel venuePanel = new JPanel();
        venuePanel.add(new JLabel("Select Venue:"));
        JComboBox<String> venueSelect = new JComboBox<>(new String[]{"Main Hall", "Small Hall"});
        venueSelect.addActionListener(e -> {
            String selectedVenue = (String) venueSelect.getSelectedItem();
            SeatingMap seating = backend.getSeatingConfiguration(selectedVenue);
            updateSeatingDisplay(seating);
        });
        venuePanel.add(venueSelect);

        // Configuration selection
        JPanel configPanel = new JPanel();
        configPanel.add(new JLabel("Configuration:"));
        JComboBox<String> configSelect = new JComboBox<>(new String[]{"Theater", "Film", "Wedding", "Classroom"});
        configPanel.add(configSelect);

        // Seating chart
        JPanel seatingChart = new JPanel(new GridLayout(15, 15));
        seatingChart.setBorder(BorderFactory.createTitledBorder("Seating Chart"));

        // Add sample seats (simplified for demo)
        for (int row = 1; row <= 15; row++) {
            for (int seat = 1; seat <= 15; seat++) {
                JButton seatButton = new JButton(row + "-" + seat);
                seatButton.setPreferredSize(new Dimension(30, 30));
                if (row == 1 || row == 12) { // Rows A and L for wheelchair spaces
                    seatButton.setBackground(Color.ORANGE);
                }
                seatingChart.add(seatButton);
            }
        }

        // Seat controls
        JPanel seatControlPanel = new JPanel();
        JButton markRestricted = new JButton("Mark as Restricted View");
        JButton markWheelchair = new JButton("Mark as Wheelchair Space");
        JButton resetSeats = new JButton("Reset Seats");
        seatControlPanel.add(markRestricted);
        seatControlPanel.add(markWheelchair);
        seatControlPanel.add(resetSeats);

        // Add components to main panel
        panel.add(venuePanel, BorderLayout.NORTH);
        panel.add(configPanel, BorderLayout.SOUTH);
        panel.add(new JScrollPane(seatingChart), BorderLayout.CENTER);
        panel.add(seatControlPanel, BorderLayout.EAST);

        return panel;
    }

    private void updateSeatingDisplay(SeatingMap seating) {
        // TODO: Implement seating visualization based on backend data
    }

    private JPanel createDailySheetsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Date selection
        JPanel datePanel = new JPanel();
        datePanel.add(new JLabel("Select Date:"));
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        datePanel.add(dateSpinner);

        JButton generateSheet = new JButton("Generate Daily Sheet");

        // Daily sheet content
        JTextArea sheetContent = new JTextArea(20, 60);
        sheetContent.setEditable(false);

        // Add action listener to the button
        generateSheet.addActionListener(e -> {
            // Get the selected date
            java.util.Date selectedDate = (java.util.Date) dateSpinner.getValue();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(selectedDate);

            // Generate sample content (in real app, this would come from your backend)
            String sampleContent = "Daily Sheet for " + formattedDate + "\n\n";
            sampleContent += "8:00 AM - Venue opens\n";
            sampleContent += "9:00 AM - Client arrival (The Phantom of the Opera)\n";
            sampleContent += "10:00 AM - Setup begins (Main Hall - Theater configuration)\n";
            sampleContent += "2:00 PM - Matinee performance begins\n";
            sampleContent += "5:00 PM - Cleanup after matinee\n";
            sampleContent += "7:00 PM - Evening performance begins\n";
            sampleContent += "10:00 PM - Venue closes\n";

            sheetContent.setText(sampleContent);
        });

        datePanel.add(generateSheet);

        JScrollPane sheetScroll = new JScrollPane(sheetContent);

        // Print/export options
        JPanel exportPanel = new JPanel();
        JButton printSheet = new JButton("Print");
        printSheet.addActionListener(e -> {
            try {
                sheetContent.print();
            } catch (java.awt.print.PrinterException ex) {
                JOptionPane.showMessageDialog(this, "Printing failed: " + ex.getMessage(),
                        "Print Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton exportPDF = new JButton("Export as PDF");
        exportPDF.addActionListener(e -> {
            // In a real app, you would implement PDF generation here
            JOptionPane.showMessageDialog(this, "PDF export would be implemented here",
                    "Export", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton exportExcel = new JButton("Export as Excel");
        exportExcel.addActionListener(e -> {
            // In a real app, you would implement Excel export here
            JOptionPane.showMessageDialog(this, "Excel export would be implemented here",
                    "Export", JOptionPane.INFORMATION_MESSAGE);
        });

        exportPanel.add(printSheet);
        exportPanel.add(exportPDF);
        exportPanel.add(exportExcel);

        // Add components to main panel
        panel.add(datePanel, BorderLayout.NORTH);
        panel.add(sheetScroll, BorderLayout.CENTER);
        panel.add(exportPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createFinancialPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Time period selection
        JPanel periodPanel = new JPanel();
        periodPanel.add(new JLabel("From:"));
        JSpinner fromDate = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor fromEditor = new JSpinner.DateEditor(fromDate, "yyyy-MM-dd");
        fromDate.setEditor(fromEditor);
        periodPanel.add(fromDate);

        periodPanel.add(new JLabel("To:"));
        JSpinner toDate = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor toEditor = new JSpinner.DateEditor(toDate, "yyyy-MM-dd");
        toDate.setEditor(toEditor);
        periodPanel.add(toDate);

        JButton generateReport = new JButton("Generate Report");
        periodPanel.add(generateReport);

        // Report tabs
        JTabbedPane reportTabs = new JTabbedPane();

        // Revenue breakdown
        JPanel revenuePanel = new JPanel(new BorderLayout());
        JTable revenueTable = new JTable(new Object[][]{
                {"Main Hall", "$15,000", "$8,000", "$23,000"},
                {"Small Hall", "$5,000", "$2,500", "$7,500"},
                {"Meeting Rooms", "$3,000", "$0", "$3,000"},
                {"Total", "$23,000", "$10,500", "$33,500"}
        }, new Object[]{"Venue", "Hire Revenue", "Ticket Revenue", "Total"});
        revenuePanel.add(new JScrollPane(revenueTable), BorderLayout.CENTER);

        // Venue usage
        JPanel usagePanel = new JPanel(new BorderLayout());
        JTable usageTable = new JTable(new Object[][]{
                {"Main Hall", "85%", "15 days"},
                {"Small Hall", "65%", "12 days"},
                {"Meeting Rooms", "45%", "10 days"}
        }, new Object[]{"Venue", "Utilization", "Days Booked"});
        usagePanel.add(new JScrollPane(usageTable), BorderLayout.CENTER);

        // Add tabs
        reportTabs.addTab("Revenue", revenuePanel);
        reportTabs.addTab("Venue Usage", usagePanel);

        // Add components to main panel
        panel.add(periodPanel, BorderLayout.NORTH);
        panel.add(reportTabs, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createReviewsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Review controls
        JPanel controlPanel = new JPanel();
        JButton fetchReviews = new JButton("Fetch New Reviews");
        JComboBox<String> reviewFilter = new JComboBox<>(new String[]{"All Reviews", "Venue Reviews", "Show Reviews"});
        controlPanel.add(fetchReviews);
        controlPanel.add(new JLabel("Filter:"));
        controlPanel.add(reviewFilter);

        // Review list
        JPanel reviewListPanel = new JPanel(new BorderLayout());
        JTable reviewTable = new JTable(new Object[][]{
                {"John Smith", "Great venue!", "Venue", "5", "2025-02-10"},
                {"Sarah Johnson", "Amazing performance", "Phantom of the Opera", "4", "2025-02-12"},
                {"Mike Brown", "Comfortable seats", "Venue", "4", "2025-02-15"}
        }, new Object[]{"Reviewer", "Comment", "Subject", "Rating", "Date"});
        reviewListPanel.add(new JScrollPane(reviewTable), BorderLayout.CENTER);

        // Reply panel
        JPanel replyPanel = new JPanel(new BorderLayout());
        replyPanel.setBorder(BorderFactory.createTitledBorder("Reply to Review"));
        JTextArea replyText = new JTextArea(5, 40);
        JButton sendReply = new JButton("Send Reply");
        replyPanel.add(new JScrollPane(replyText), BorderLayout.CENTER);
        replyPanel.add(sendReply, BorderLayout.SOUTH);

        // Add components to main panel
        panel.add(controlPanel, BorderLayout.NORTH);
        panel.add(reviewListPanel, BorderLayout.CENTER);
        panel.add(replyPanel, BorderLayout.SOUTH);

        return panel;
    }

    // Helper methods
    private void showDayDetails(int day) {
        JOptionPane.showMessageDialog(this, "Details for day " + day, "Day Details", JOptionPane.INFORMATION_MESSAGE);
    }

    // Sample backend service simulation
    class BackendService {
        // TODO: Backend team to implement these methods

        public List<Booking> getBookings(Date start, Date end) {
            // Sample data
            return Arrays.asList(
                    new Booking("Phantom of the Opera", "Main Hall", new Date(), new Date()),
                    new Booking("Corporate Meeting", "Meeting Room 3", new Date(), new Date())
            );
        }

        public boolean saveBooking(BookingDetails details) {
            // TODO: Implement persistence
            return true;
        }

        public SeatingMap getSeatingConfiguration(String venue) {
            // Sample seating map
            return new SeatingMap(venue);
        }
    }

    // Data models needed from backend
    static class Booking {
        String eventName;
        String venue;
        Date startDate;
        Date endDate;

        // TODO: Add all fields from specifications
        public Booking(String name, String venue, Date start, Date end) {
            this.eventName = name;
            this.venue = venue;
            this.startDate = start;
            this.endDate = end;
        }
    }

    static class SeatingMap {
        String venue;
        boolean[][] wheelchairSpaces;
        boolean[][] restrictedViews;

        public SeatingMap(String venue) {
            // TODO: Implement actual seat mapping
            this.venue = venue;
        }
    }

    static class BookingDetails {
        String clientName;
        String eventType;
        Date startDate;
        Date endDate;
        List<String> rooms;
        String configuration;
        int setupDays;
        double revenueShare;

        // TODO: Add validation methods per specifications
    }
}