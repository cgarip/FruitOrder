package dao;

import model.User;

public interface IUserDAO {
    boolean registerUser(User user);
    User loginUser(String username, String password);
}
