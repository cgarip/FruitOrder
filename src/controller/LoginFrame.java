package controller;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginFrame() {
        setTitle("Login");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 30, 100, 25);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(130, 30, 180, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 70, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(130, 70, 180, 25);
        add(passwordField);

        loginButton = new JButton("登入");
        loginButton.setBounds(50, 120, 100, 30);
        add(loginButton);

        registerButton = new JButton("註冊");
        registerButton.setBounds(180, 120, 100, 30);
        add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                UserDAO userDAO = new UserDAO();
                User user = userDAO.loginUser(username, password);
                if (user != null) {
                    new OrderFrame(user).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Credentials!");
                }
            }
        });

        registerButton.addActionListener(e -> {
            new RegisterFrame().setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
}
