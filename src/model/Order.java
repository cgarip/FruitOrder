package model;

public class Order {
    private int id;
    private String fruitName;
    private int quantity;
    private double price;
    private int userId;

    // Constructor
    public Order(int id, String fruitName, int quantity, double price, int userId) {
        this.id = id;
        this.fruitName = fruitName;
        this.quantity = quantity;
        this.price = price;
        this.userId = userId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", fruitName='" + fruitName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", userId=" + userId +
                '}';
    }
}
