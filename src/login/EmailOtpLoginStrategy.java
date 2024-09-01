package login;

import UserModule.*;

public class EmailOtpLoginStrategy implements LoginStrategy {

    private final UserManager userManager;

    public EmailOtpLoginStrategy()
    {
        this.userManager = UserManager.getInstance();
    }

    @Override
    public User processLogin(UserMetaData userMetaData) {
        return userManager.getUserByEmail(userMetaData.getEmail());
    }
}
