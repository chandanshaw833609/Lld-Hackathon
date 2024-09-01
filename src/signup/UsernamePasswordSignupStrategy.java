package signup;

import UserModule.*;

public class UsernamePasswordSignupStrategy implements SignupStrategy {

    private final UserManager userManager;

    public UsernamePasswordSignupStrategy()
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
