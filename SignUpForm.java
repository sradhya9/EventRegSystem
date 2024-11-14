package eventregsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private String userType;

    public SignUpForm(String userType) {
        this.userType = userType;

        setTitle("Sign Up - " + userType);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(250, 250, 250)); // Light background color for a fresh look

        // Title Label
        JLabel titleLabel = new JLabel("Sign Up - " + userType, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(44, 62, 80)); // Dark color for text
        titleLabel.setBounds(0, 20, 400, 40);

        // Username Label and TextField
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameLabel.setBounds(50, 80, 100, 30);
        usernameLabel.setForeground(new Color(97, 112, 128)); // Subtle grayish text

        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBounds(150, 80, 200, 30);
        usernameField.setBackground(Color.WHITE);
        usernameField.setForeground(new Color(44, 62, 80)); // Dark text for clarity
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204))); // Subtle border

        // Password Label and PasswordField
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setBounds(50, 120, 100, 30);
        passwordLabel.setForeground(new Color(97, 112, 128)); // Subtle grayish text

        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBounds(150, 120, 200, 30);
        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(new Color(44, 62, 80)); // Dark text for clarity
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204))); // Subtle border

        // Sign Up Button
        JButton signupButton = new JButton("Sign Up");
        signupButton.setFont(new Font("Arial", Font.BOLD, 14));
        signupButton.setBounds(150, 180, 100, 40);
        signupButton.setBackground(new Color(46, 204, 113)); // Light green, professional and calm
        signupButton.setForeground(Color.WHITE);
        signupButton.setBorder(BorderFactory.createEmptyBorder());
        signupButton.setFocusPainted(false);
        signupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Adding components to the frame
        add(titleLabel);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(signupButton);

        // Action listener for signup button
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        UserDao userDAO = new UserDao();

        if (userDAO.registerUser(username, password, userType)) {
            JOptionPane.showMessageDialog(this, "Registration successful! Please log in.");
            new LoginForm(userType).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed. Please try again.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SignUpForm("Student").setVisible(true); // Change "Student" to test with other user types
            }
        });
    }
}
