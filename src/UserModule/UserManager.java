package UserModule;

import java.util.*;

public class UserManager {
    private static UserManager userManager;
    private final Map<String, User> usersMap = new HashMap<>();

    private UserManager() {}

    public static UserManager getInstance() {
        if (userManager == null) {
            userManager = new UserManager();
        }
        return userManager;
    }

    public User getUserById(String userId) {
        return usersMap.get(userId);
    }

    public void saveUser(User user) {
        usersMap.put(user.getId(), user);
    }

    public User getUserByName(String name) {
        Optional<User> optionalUser = usersMap
                .values()
                .stream()
                .filter(user -> user
                        .getName()
                        .equalsIgnoreCase(name))
                .findFirst();
        return optionalUser.orElse(null);
    }

    public User getUserByEmail(String email) {
        Optional<User> optionalUser =  usersMap
                .values()
                .stream()
                .filter(user -> user
                        .getEmail()
                        .equalsIgnoreCase(email))
                .findFirst();
        return optionalUser.orElse(null);
    }

    public List<User> getAllUser() {
        return new ArrayList<>(usersMap.values());
    }

    public void removeUser(String userId) {
        usersMap.remove(userId);
    }
}
