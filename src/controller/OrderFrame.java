package controller;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import dao.OrderDAO;
import model.Order;
import model.User;

public class OrderFrame extends JFrame {
    private JTextField fruitNameField, quantityField, priceField;
    private JButton addButton, updateButton, deleteButton, clearButton, printButton, logoutButton;
    private JTable orderTable;
    private DefaultTableModel tableModel;
    private User user;
    private OrderDAO orderDAO;
    private JLabel userInfoLabel, timeLabel;

    public OrderFrame(User user) {
        this.user = user;
        orderDAO = new OrderDAO();

        setTitle("Order Management");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        // User info label
        userInfoLabel = new JLabel("Logged in as: " + user.getUsername() + " | " + user.getPhone() + " | " + user.getEmail());
        userInfoLabel.setBounds(20, 10, 550, 25);
        userInfoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        getContentPane().add(userInfoLabel);

        // Real-time time label (top-right)
        timeLabel = new JLabel("Time: ");
        timeLabel.setBounds(450, 10, 120, 25);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        getContentPane().add(timeLabel);

        // Timer to update time every second
        Timer timer = new Timer(1000, e -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            timeLabel.setText("Time: " + sdf.format(new Date()));
        });
        timer.start();

        // Fruit Name
        JLabel fruitNameLabel = new JLabel("Fruit Name:");
        fruitNameLabel.setBounds(20, 50, 100, 25);
        getContentPane().add(fruitNameLabel);

        fruitNameField = new JTextField();
        fruitNameField.setBounds(130, 50, 150, 25);
        getContentPane().add(fruitNameField);

        // Quantity
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(20, 90, 100, 25);
        getContentPane().add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(130, 90, 150, 25);
        getContentPane().add(quantityField);

        // Price
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 130, 100, 25);
        getContentPane().add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(130, 130, 150, 25);
        getContentPane().add(priceField);

        // Buttons
        addButton = new JButton("新增");
        addButton.setBounds(300, 50, 120, 30);
        getContentPane().add(addButton);

        updateButton = new JButton("更新");
        updateButton.setBounds(300, 90, 120, 30);
        getContentPane().add(updateButton);

        deleteButton = new JButton("刪除");
        deleteButton.setBounds(300, 130, 120, 30);
        getContentPane().add(deleteButton);

        clearButton = new JButton("清除");
        clearButton.setBounds(450, 46, 120, 30);
        getContentPane().add(clearButton);

        printButton = new JButton("列印");
        printButton.setBounds(450, 87, 120, 30);
        getContentPane().add(printButton);

        logoutButton = new JButton("登出");
        logoutButton.setBounds(450, 127, 100, 30);
        getContentPane().add(logoutButton);

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Fruit Name", "Quantity", "Price"}, 0);
        orderTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // Disable editing of all cells
            }
        };
        JScrollPane scrollPane = new JScrollPane(orderTable);
        scrollPane.setBounds(20, 180, 550, 200);
        getContentPane().add(scrollPane);

        loadOrders();

        // Add Order
        addButton.addActionListener(e -> {
            String fruitName = fruitNameField.getText();
            String quantityText = quantityField.getText();
            String priceText = priceField.getText();

            if (fruitName.isEmpty() || quantityText.isEmpty() || priceText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled!");
                return;
            }

            try {
                int quantity = Integer.parseInt(quantityText);
                double price = Double.parseDouble(priceText);

                Order newOrder = new Order(0, fruitName, quantity, price, user.getId());
                orderDAO.addOrder(newOrder);
                loadOrders();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Quantity and Price must be numeric!");
            }
        });

        // Update Order
        updateButton.addActionListener(e -> {
            int selectedRow = orderTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select an order to update!");
                return;
            }

            // Get selected order ID
            int orderId = (int) tableModel.getValueAt(selectedRow, 0);

            // Get new values from input fields
            String fruitName = fruitNameField.getText().trim();
            String quantityText = quantityField.getText().trim();
            String priceText = priceField.getText().trim();

            // Input validation
            if (fruitName.isEmpty() || quantityText.isEmpty() || priceText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled!");
                return;
            }

            try {
                int quantity = Integer.parseInt(quantityText);
                double price = Double.parseDouble(priceText);

                // Create updated Order object
                Order updatedOrder = new Order(orderId, fruitName, quantity, price, user.getId());

                // Update order in DB
                boolean success = orderDAO.updateOrder(updatedOrder);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Order updated successfully!");
                    loadOrders(); // Refresh table
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update order!");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Quantity and Price must be numeric!");
            }
        });

        // Delete Order
        deleteButton.addActionListener(e -> {
            int selectedRow = orderTable.getSelectedRow();
            if (selectedRow != -1) {
                int orderId = (int) tableModel.getValueAt(selectedRow, 0);
                orderDAO.deleteOrder(orderId);
                loadOrders();
            } else {
                JOptionPane.showMessageDialog(null, "Please select an order to delete!");
            }
        });

        // Clear Fields
        clearButton.addActionListener(e -> {
            fruitNameField.setText("");
            quantityField.setText("");
            priceField.setText("");
        });

        // Print Table
        printButton.addActionListener(e -> {
            try {
                boolean printSuccess = orderTable.print();
                if (printSuccess) {
                    JOptionPane.showMessageDialog(null, "Print successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Print failed.");
                }
            } catch (PrinterException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error printing table.");
            }
        });

        // Logout
        logoutButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });

        // Row Selection Listener to synchronize fields with selected row
        orderTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = orderTable.getSelectedRow();
                if (selectedRow != -1) {
                    fruitNameField.setText((String) tableModel.getValueAt(selectedRow, 1));
                    quantityField.setText(String.valueOf(tableModel.getValueAt(selectedRow, 2)));
                    priceField.setText(String.valueOf(tableModel.getValueAt(selectedRow, 3)));
                }
            }
        });
    }

    private void loadOrders() {
        tableModel.setRowCount(0);
        List<Order> orders = orderDAO.getOrdersByUser(user.getId());
        for (Order order : orders) {
            tableModel.addRow(new Object[]{order.getId(), order.getFruitName(), order.getQuantity(), order.getPrice()});
        }
    }
}
