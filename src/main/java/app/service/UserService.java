package app.service;

import app.model.User;
import app.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public User getExampleUser() {
        return new User(1265, "Jane Doe", "jane.doe@example.com");
    }

    public User getDefaultUser() {
        return userRepository.findById(1);
    }

    public static String toJson(User user) {
        return "{"
                + "\"id\":" + user.getId() + ","
                + "\"name\":\"" + user.getName() + "\","
                + "\"email\":\"" + user.getEmail() + "\""
                + "}";
    }
}
