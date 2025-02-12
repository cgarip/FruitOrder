package model;
public class User {
    private int id;
    private String username;
    private String password;
    private String phone;
    private String email;

    public User(int id, String username, String password, String phone, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
}
