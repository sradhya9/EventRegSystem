package eventregsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class AdminDashboard extends JFrame {
    private JComboBox<Event> eventComboBox;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton viewAllButton;
    private JButton backButton;

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(530, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(248, 248, 255));

        // Set Font
        Font buttonFont = new Font("Segoe UI", Font.PLAIN, 14);

        // ComboBox for events
        eventComboBox = new JComboBox<>();
        eventComboBox.setBounds(50, 30, 415, 30);
        eventComboBox.setFont(buttonFont);
        eventComboBox.setBackground(new Color(255, 255, 255));

        // Add Event Button
        addButton = new JButton("Add Event");
        addButton.setFont(buttonFont);
        addButton.setBackground(new Color(77, 182, 172));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setBorder(BorderFactory.createLineBorder(new Color(50, 150, 200), 2));

        // Update Event Button
        updateButton = new JButton("Update Event");
        updateButton.setFont(buttonFont);
        updateButton.setBackground(new Color(77, 182, 172));
        updateButton.setForeground(Color.WHITE);
        updateButton.setFocusPainted(false);
        updateButton.setBorder(BorderFactory.createLineBorder(new Color(50, 150, 200), 2));

        // Delete Event Button
        deleteButton = new JButton("Delete Event");
        deleteButton.setFont(buttonFont);
        deleteButton.setBackground(new Color(77, 182, 172));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.setBorder(BorderFactory.createLineBorder(new Color(50, 150, 200), 2));

        // View All Events Button
        viewAllButton = new JButton("View All Events");
        viewAllButton.setFont(buttonFont);
        viewAllButton.setBackground(new Color(77, 182, 172));
        viewAllButton.setForeground(Color.WHITE);
        viewAllButton.setFocusPainted(false);
        viewAllButton.setBorder(BorderFactory.createLineBorder(new Color(50, 150, 140), 2));

        // Back Button (Red to match the other screen)
        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setBackground(new Color(52, 152, 219));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2));

        // Set button bounds and alignment
        int buttonWidth = 120;
        int buttonHeight = 40;
        int horizontalMargin = 50;
        int verticalMargin = 80;
        int gap = 30;

        addButton.setBounds(50, 80, 120, 40);
        updateButton.setBounds(200, 80,120, 40);
        deleteButton.setBounds(350, 80, 120, 40);

        // View All Events Button aligned below the above buttons
        viewAllButton.setBounds(horizontalMargin, verticalMargin + buttonHeight + gap, 3 * buttonWidth + 2 * gap, buttonHeight);

        // Back Button aligned at the bottom center
        backButton.setBounds(200, 300, 120, 40);

        // Add components to the frame
        add(eventComboBox);
        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(viewAllButton);
        add(backButton);

        // Populate the events combo box
        populateEvents();

        // Action listener for adding a new event
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = JOptionPane.showInputDialog("Enter Event Name:");
                if (eventName == null || eventName.trim().isEmpty()) return;

                try {
                    Date eventDate = Date.valueOf(JOptionPane.showInputDialog("Enter Event Date (yyyy-mm-dd):"));
                    String description = JOptionPane.showInputDialog("Enter Description:");
                    EventDao eventDAO = new EventDao();
                    if (eventDAO.addEvent(eventName, eventDate, description)) {
                        JOptionPane.showMessageDialog(null, "Event added successfully!");
                        populateEvents();
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Please use yyyy-mm-dd.");
                }
            }
        });

        // Action listener for updating an event
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Event selectedEvent = (Event) eventComboBox.getSelectedItem();
                if (selectedEvent != null) {
                    String newEventName = JOptionPane.showInputDialog("Enter New Event Name:", selectedEvent.getEventName());
                    if (newEventName == null || newEventName.trim().isEmpty()) return;

                    try {
                        Date newEventDate = Date.valueOf(JOptionPane.showInputDialog("Enter New Event Date (yyyy-mm-dd):", selectedEvent.getEventDate().toString()));
                        String newDescription = JOptionPane.showInputDialog("Enter New Description:", selectedEvent.getDescription());
                        EventDao eventDAO = new EventDao();
                        if (eventDAO.updateEvent(selectedEvent.getEventId(), newEventName, newEventDate, newDescription)) {
                            JOptionPane.showMessageDialog(null, "Event updated successfully!");
                            populateEvents();
                        }
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid date format. Please use yyyy-mm-dd.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No event selected.");
                }
            }
        });

        // Action listener for deleting an event
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Event selectedEvent = (Event) eventComboBox.getSelectedItem();
                if (selectedEvent != null) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this event?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        EventDao eventDAO = new EventDao();
                        if (eventDAO.deleteEvent(selectedEvent.getEventId())) {
                            JOptionPane.showMessageDialog(null, "Event deleted successfully!");
                            populateEvents();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No event selected.");
                }
            }
        });

        // Action listener for viewing all events
        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventDao eventDAO = new EventDao();
                List<Event> events = eventDAO.getAllEvents();
                StringBuilder allEvents = new StringBuilder("All Events:\n");

                for (Event event : events) {
                    allEvents.append(event.toString()).append("\n");
                }

                JOptionPane.showMessageDialog(null, allEvents.toString());
            }
        });

        // Back button action listener
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WelcomePage().setVisible(true);
                dispose();
            }
        });
    }

    // Helper method to populate the combo box with events
    private void populateEvents() {
        eventComboBox.removeAllItems();
        EventDao eventDAO = new EventDao();
        List<Event> events = eventDAO.getAllEvents();
        for (Event event : events) {
            eventComboBox.addItem(event);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminDashboard().setVisible(true);
            }
        });
    }
}
