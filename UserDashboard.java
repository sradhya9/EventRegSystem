package eventregsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserDashboard extends JFrame {
    private JComboBox<Event> eventComboBox;
    private JButton viewDetailsButton;
    private JButton registerButton, backButton;
    private int userId;
    private String userType;
    private String username; // Assuming this is set or passed in the constructor

    public UserDashboard(int userId, String userType, String username) {
        this.userId = userId;
        this.userType = userType;
        this.username = username;

        setTitle(userType + " Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Set the background color of the frame for a soft, light appearance
        getContentPane().setBackground(new Color(243, 243, 243)); // Light gray background

        // Event ComboBox setup
        eventComboBox = new JComboBox<>();
        eventComboBox.setBounds(50, 30, 300, 30);
        eventComboBox.setBackground(Color.WHITE); // White background for the combo box
        eventComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Buttons setup
        viewDetailsButton = new JButton("View Details");
        viewDetailsButton.setBounds(50, 80, 150, 30);
        viewDetailsButton.setBackground(new Color(70, 130, 180)); // Light blue button
        viewDetailsButton.setForeground(Color.WHITE);
        viewDetailsButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        viewDetailsButton.setFocusPainted(false);
        viewDetailsButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        registerButton = new JButton("Register");
        registerButton.setBounds(210, 80, 150, 30);
        registerButton.setBackground(new Color(60, 179, 113)); // Green button
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        registerButton.setFocusPainted(false);
        registerButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        backButton = new JButton("Back");
        backButton.setBounds(150, 200, 100, 30); // Adjust position as needed
        backButton.setBackground(new Color(255, 99, 71)); // Red button
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Populate events combo box
        populateEvents();

        add(eventComboBox);
        add(viewDetailsButton);
        add(registerButton);
        add(backButton);

        // Action listeners for buttons
        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Event selectedEvent = (Event) eventComboBox.getSelectedItem();
                if (selectedEvent != null) {
                    JOptionPane.showMessageDialog(null, selectedEvent.toString());
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerForEvent();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WelcomePage().setVisible(true);
                dispose();
            }
        });
    }

    // Populate events in combo box
    private void populateEvents() {
        EventDao eventDAO = new EventDao();
        List<Event> events = eventDAO.getAllEvents();
        for (Event event : events) {
            eventComboBox.addItem(event);
        }
    }

    // Handle event registration process
    private void registerForEvent() {
        Event selectedEvent = (Event) eventComboBox.getSelectedItem();
        RegistrationDAO registrationDAO = new RegistrationDAO();

        if (selectedEvent != null) {
            if (registrationDAO.isUserRegistered(userId, selectedEvent.getEventId())) {
                JOptionPane.showMessageDialog(this, "You are already registered for this event.");
                return;
            }

            int response = JOptionPane.showConfirmDialog(this, 
                "Would you like to pay for this event registration?", 
                "Confirm Payment", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                String eventName = selectedEvent.getEventName();
                String eventDate = selectedEvent.getEventDate();

                if (registrationDAO.addRegistration(userId, selectedEvent.getEventId(), "paid", username, eventName, eventDate)) {
                    JOptionPane.showMessageDialog(this, "Registration successful and payment confirmed!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to register. Please try again.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Payment cancelled.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an event to register.");
        }
    }
}
