package login;

import UserModule.User;
import UserModule.UserManager;
import UserModule.UserMetaData;

public class EmailLoginStrategy implements LoginStrategy {

    UserManager userManager;

    public EmailLoginStrategy()
    {
        this.userManager = UserManager.getUserManagerInstance();
    }

    @Override
    public User processLogin(UserMetaData userMetaData) {
        return userManager.getUserByEmail(userMetaData.getEmail());
    }
}
