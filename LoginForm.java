package eventregsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private String userType;
    private JButton backButton;

    public LoginForm(String userType) {
        this.userType = userType;

        setTitle("Login - " + userType);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(250, 250, 250)); // Light background for a fresh look

        // Title Label
        JLabel titleLabel = new JLabel("Login - " + userType, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(44, 62, 80)); // Dark color for title text
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

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBounds(220, 180, 100, 40);
        loginButton.setBackground(new Color(46, 204, 113)); // Light green color for positive action
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setFocusPainted(false);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Back Button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBounds(100, 180, 100, 40);
        backButton.setBackground(new Color(52, 152, 219)); // Light blue for back action
        backButton.setForeground(Color.WHITE);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setFocusPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Adding components to the frame
        add(titleLabel);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(backButton);

        // Action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        // Action listener for back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WelcomePage().setVisible(true);
                dispose(); // Close the current LoginForm window
            }
        });
    }

    private void loginUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        UserDao userDAO = new UserDao();

        if (userDAO.loginUser(username, password)) {
            String role = userDAO.getUserRole(username);

            if (role.equals(userType)) {
                JOptionPane.showMessageDialog(this, "Login successful!");

                int userId = userDAO.getUserId(username);

                if (userType.equals("Admin")) {
                    AdminDashboard adminDashboard = new AdminDashboard();
                    adminDashboard.setVisible(true);
                } else {
                    UserDashboard userDashboard = new UserDashboard(userId, userType, username);
                    userDashboard.setVisible(true);
                }

                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect role selected. Please try again.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginForm("Student").setVisible(true); // Change "Student" to test with other user types
            }
        });
    }
}
