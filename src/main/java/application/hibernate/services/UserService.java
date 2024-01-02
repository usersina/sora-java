package application.hibernate.services;

import java.util.List;

import application.hibernate.entities.User;

public interface UserService {
    User saveUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    User findUserByEmailOrUsername(String emailOrUsername);
}
