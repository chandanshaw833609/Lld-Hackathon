package login;

import user.User;
import user.UserManager;

public class UsernamePasswordLoginStrategy implements LoginStrategy {

    private final UserManager userManager;

    public UsernamePasswordLoginStrategy()
    {
        this.userManager = UserManager.getInstance();
    }

    @Override
    public User performLogin(LoginDetails loginDetails) {
        UsernamePasswordLoginDetails details = (UsernamePasswordLoginDetails) loginDetails;
        String username = details.username();
        String password = details.password();
        User user = userManager.getUserByUsername(username);

        if (user == null) {
            return null;
        }
        // otherwise match password
        return user.getPassword().equals(password) ? user : null;
    }
}
