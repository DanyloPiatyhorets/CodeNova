package view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private JTabbedPane tabbedPane;

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

        // Calendar view implementation
        // ... (same as previous implementation)

        return panel;
    }

    private JPanel createBookingsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Bookings implementation
        // ... (same as previous implementation)

        return panel;
    }

    private JPanel createSeatingPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Seating implementation
        // ... (same as previous implementation)

        return panel;
    }

    private JPanel createDailySheetsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Daily sheets implementation
        // ... (same as previous implementation)

        return panel;
    }

    private JPanel createFinancialPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Financial implementation
        // ... (same as previous implementation)

        return panel;
    }

    private JPanel createReviewsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Reviews implementation
        // ... (same as previous implementation)

        return panel;
    }

    // Helper methods
    private void showDayDetails(int day) {
        JOptionPane.showMessageDialog(this, "Details for day " + day, "Day Details", JOptionPane.INFORMATION_MESSAGE);
    }
}