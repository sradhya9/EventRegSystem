package eventregsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends JFrame {
    private JComboBox<String> userTypeComboBox;
    private JButton loginButton;
    private JButton signupButton;

    public WelcomePage() {
        setTitle("Event Registration System");
        setSize(450, 400);  // Slightly larger window for better spacing
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(250, 250, 250)); // Light background color for a fresh look

        // Title Label
        JLabel titleLabel = new JLabel("EVENT REGISTRATION SYSTEM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(44, 62, 80)); // Darker shade for text
        titleLabel.setBounds(0, 30, 450, 40);

        // Welcome Text
        JLabel welcomeLabel = new JLabel("Welcome! Please select user type and choose an option.", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        welcomeLabel.setForeground(new Color(97, 112, 128)); // Subtle gray for text
        welcomeLabel.setBounds(0, 90, 450, 30);

        // User Type ComboBox
        userTypeComboBox = new JComboBox<>(new String[] {"Student", "Staff", "Admin"});
        userTypeComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        userTypeComboBox.setBounds(170, 130, 120, 30);
        userTypeComboBox.setBackground(new Color(255, 255, 255)); // Clean white background for combo box
        userTypeComboBox.setForeground(new Color(44, 62, 80)); // Consistent dark text color
        userTypeComboBox.setFocusable(false);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBounds(100, 200, 100, 40);
        loginButton.setBackground(new Color(41, 128, 185));  // Light blue for a professional yet inviting look
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setFocusPainted(false);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Sign Up Button
        signupButton = new JButton("Sign Up");
        signupButton.setFont(new Font("Arial", Font.BOLD, 14));
        signupButton.setBounds(250, 200, 100, 40);
        signupButton.setBackground(new Color(46, 204, 113));  // Light green, professional and calm
        signupButton.setForeground(Color.WHITE);
        signupButton.setBorder(BorderFactory.createEmptyBorder());
        signupButton.setFocusPainted(false);
        signupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Adding components to the frame
        add(titleLabel);
        add(welcomeLabel);
        add(userTypeComboBox);
        add(loginButton);
        add(signupButton);

        // Action listeners for buttons
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginPage();
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSignupPage();
            }
        });
    }

    private void openLoginPage() {
        String userType = (String) userTypeComboBox.getSelectedItem();
        new LoginForm(userType).setVisible(true);
        dispose();
    }

    private void openSignupPage() {
        String userType = (String) userTypeComboBox.getSelectedItem();
        new SignUpForm(userType).setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WelcomePage().setVisible(true);
            }
        });
    }
}
