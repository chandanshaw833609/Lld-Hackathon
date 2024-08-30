package signup;

import UserModule.User;
import UserModule.UserManager;
import UserModule.UserMetaData;

public class EmailSignupStrategy implements SignupStrategy {
    UserManager userManager;
    public EmailSignupStrategy()
    {
        this.userManager = UserManager.getUserManagerInstance();
    }
    @Override
    public User processSignup(UserMetaData userMetaData) {
        User user = new User(userMetaData.getName(),userMetaData.getRole());
        user.setEmail(user.getEmail());
        userManager.addUser(user);
        return user;
    }
}
