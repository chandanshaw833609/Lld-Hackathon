package login;

import UserModule.*;

public class EmailLoginStrategy implements LoginStrategy {

    UserManager userManager;

    public EmailLoginStrategy()
    {
        this.userManager = UserManager.getInstance();
    }

    @Override
    public User processLogin(UserMetaData userMetaData) {
        return userManager.getUserByEmail(userMetaData.getEmail());
    }
}
