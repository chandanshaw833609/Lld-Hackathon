package signup;

import UserModule.User;
import UserModule.UserManager;
import UserModule.UserMetaData;

public class PasswordSignupStrategy implements SignupStrategy {

    UserManager userManager;

    public PasswordSignupStrategy()
    {
        this.userManager = UserManager.getUserManagerInstance();
    }

    @Override
    public User processSignup(UserMetaData userMetaData) {
        User user = new User(userMetaData.getName(),userMetaData.getRole());
        user.setPassword(userMetaData.getPassword());
        userManager.addUser(user);
        return user;
    }
}
