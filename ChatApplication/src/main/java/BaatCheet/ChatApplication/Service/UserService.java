package BaatCheet.ChatApplication.Service;

import BaatCheet.ChatApplication.Entity.User;

public interface UserService {

    User registerUser(User user);

    boolean usernameExists(String username);

    boolean emailExists(String email);
}