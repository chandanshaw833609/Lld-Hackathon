package UserModule;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserManager {
    Map<String, User> users;
    public static UserManager userManager;
    private UserManager() {
        users = new HashMap<>();
    }

    public static UserManager getUserManager() {
        if (userManager == null) {
            userManager = new UserManager();
        }
        return userManager;
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public User getUserById(String id) {
        return users.get(id);
    }

    public User getUserByName(String name) {
       Optional<User> optionalUser =  users.values().stream().filter(user -> user.getName().equalsIgnoreCase(name)).findFirst();
       return optionalUser.orElse(null);
    }

    public User getUserByEmail(String email) {
        Optional<User> optionalUser =  users.values().stream().filter(user -> user.getName().equalsIgnoreCase(email)).findFirst();
        return optionalUser.orElse(null);
    }
}
