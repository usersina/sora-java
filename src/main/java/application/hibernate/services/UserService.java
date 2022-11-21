package application.hibernate.services;

import java.util.List;

import application.hibernate.entities.User;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers();

    User findUserByEmailOrUsername(String emailOrUsername);
}
