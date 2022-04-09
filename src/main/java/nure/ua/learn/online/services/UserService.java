package nure.ua.learn.online.services;

import lombok.AllArgsConstructor;
import nure.ua.learn.online.entities.User;
import nure.ua.learn.online.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void addNewUser(User user) {
        userRepository.save(user);
    }

    public boolean isUserExists(String login) {
        return userRepository.existsById(login);
    }

    public boolean checkPassword(String login, String password) {
        if (!isUserExists(login)) {
            return false;
        }
        User user = userRepository.getById(login);
        return user.getPassword().equals(password);
    }

    public Optional<User> getUser(String login) {
        if (!isUserExists(login)) {
            return Optional.empty();
        }
        return Optional.of(userRepository.getById(login));
    }

    public void updateUser(User user) {
        if (isUserExists(user.getLogin())) {
            userRepository.save(user);
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
