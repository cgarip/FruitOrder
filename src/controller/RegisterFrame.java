package controller;

import dao.UserDAO;
import model.User;
import util.Validator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame {
    private JTextField usernameField, phoneField, emailField;
    private JPasswordField passwordField;
    private JButton registerButton, backButton;

    public RegisterFrame() {
        setTitle("Register");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 40, 100, 25);
        getContentPane().add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(130, 40, 180, 25);
        getContentPane().add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 80, 100, 25);
        getContentPane().add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(130, 80, 180, 25);
        getContentPane().add(passwordField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(20, 120, 100, 25);
        getContentPane().add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(130, 120, 180, 25);
        getContentPane().add(phoneField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 160, 100, 25);
        getContentPane().add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(130, 160, 180, 25);
        getContentPane().add(emailField);

        // Register Button
        registerButton = new JButton("註冊");
        registerButton.setBounds(210, 200, 100, 30);
        getContentPane().add(registerButton);

        // Back to Login Button
        backButton = new JButton("回登入頁面");
        backButton.setBounds(56, 200, 120, 30); // Positioned next to Register button
        getContentPane().add(backButton);

        // Register Action Listener
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String phone = phoneField.getText();
                String email = emailField.getText();

                if (!Validator.isValidPhone(phone)) {
                    JOptionPane.showMessageDialog(null, "Invalid phone number!");
                    return;
                }
                if (!Validator.isValidEmail(email)) {
                    JOptionPane.showMessageDialog(null, "Invalid email format!");
                    return;
                }

                UserDAO userDAO = new UserDAO();
                User user = new User(0, username, password, phone, email);
                if (userDAO.registerUser(user)) {
                    JOptionPane.showMessageDialog(null, "Registration Successful!");
                    new LoginFrame().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Registration Failed!");
                }
            }
        });

        // Back to Login Action Listener
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginFrame().setVisible(true);  // Open Login Frame
                dispose();  // Close the Register Frame
            }
        });
    }
}
