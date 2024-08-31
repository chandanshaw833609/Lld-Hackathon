package login;

import UserModule.User;
import UserModule.UserManager;
import UserModule.UserMetaData;

public class PasswordLoginStrategy implements LoginStrategy {

    UserManager userManager;

    public PasswordLoginStrategy()
    {
        this.userManager = UserManager.getInstance();
    }

    @Override
    public User processLogin(UserMetaData userMetaData) {
        User user = userManager.getUserByName(userMetaData.getName());
        return user != null && user.getPassword().equals(userMetaData.getPassword()) ? user : null;
    }
}
