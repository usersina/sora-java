package application.hibernate.services;

import java.util.List;

import application.hibernate.entities.User;
import application.hibernate.repos.UserRepository;

public class UserServiceImpl implements UserService {
    UserRepository userRepository = new UserRepository();

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByEmailOrUsername(String emailOrUsername) {
        return userRepository.findUserByEmailOrUsername(emailOrUsername);
    }
}
