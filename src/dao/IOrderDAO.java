package dao;

import model.Order;
import java.util.List;

public interface IOrderDAO {
    boolean addOrder(Order order);
    List<Order> getOrdersByUser(int userId);
    boolean updateOrder(Order order);
    boolean deleteOrder(int orderId);
}
