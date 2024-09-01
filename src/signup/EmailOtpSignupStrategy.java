package signup;

import UserModule.*;

public class EmailOtpSignupStrategy implements SignupStrategy {
    private final UserManager userManager;
    public EmailOtpSignupStrategy()
    {
        this.userManager = UserManager.getInstance();
    }
    @Override
    public User processSignup(UserMetaData userMetaData) {
        User user = new Buyer();
        user.setName(userMetaData.getName());
        user.setEmail(userMetaData.getEmail());
        userManager.saveUser(user);
        return user;
    }
}
