package login;

import UserModule.User;
import UserModule.UserManager;
import UserModule.UserMetaData;

public class PasswordLoginStrategy implements LoginStrategy {

    UserManager userManager;

    public PasswordLoginStrategy()
    {
        this.userManager = UserManager.getUserManagerInstance();
    }

    @Override
    public User processLogin(UserMetaData userMetaData) {
        User user = userManager.getUserByName(userMetaData.getName());
        if(user!=null && user.getEmail()!=null)
        {
            return user;
        }
        return userMetaData.getPassword().equals(user.getPassword()) ? user : null;
    }
}
