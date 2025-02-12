package dao;

import model.Order;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements IOrderDAO {
    @Override
    public boolean addOrder(Order order) {
        String query = "INSERT INTO orders (fruit_name, quantity, price, user_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, order.getFruitName());
            stmt.setInt(2, order.getQuantity());
            stmt.setDouble(3, order.getPrice());
            stmt.setInt(4, order.getUserId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Order> getOrdersByUser(int userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orders.add(new Order(rs.getInt("id"), rs.getString("fruit_name"),
                        rs.getInt("quantity"), rs.getDouble("price"), rs.getInt("user_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public boolean updateOrder(Order order) {
        String query = "UPDATE orders SET fruit_name = ?, quantity = ?, price = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, order.getFruitName());
            stmt.setInt(2, order.getQuantity());
            stmt.setDouble(3, order.getPrice());
            stmt.setInt(4, order.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteOrder(int orderId) {
        String query = "DELETE FROM orders WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
