package signup;

import user.*;

public class PasswordSignupStrategy implements SignupStrategy {

    UserManager userManager;

    public PasswordSignupStrategy()
    {
        this.userManager = UserManager.getInstance();
    }

    @Override
    public User processSignup(UserMetaData userMetaData) {
        User user = new Buyer();
        user.setName(userMetaData.getName());
        user.setPassword(userMetaData.getPassword());
        userManager.saveUser(user);
        return user;
    }
}
