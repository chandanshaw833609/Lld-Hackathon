package login;

import user.*;

public class EmailOtpLoginStrategy implements LoginStrategy {

    private final UserManager userManager;

    public EmailOtpLoginStrategy()
    {
        this.userManager = UserManager.getInstance();
    }

    @Override
    public User performLogin(LoginDetails loginDetails) {
        EmailOtpLoginDetails details = (EmailOtpLoginDetails) loginDetails;
        String email = details.email();
        return userManager.getUserByEmail(email);
    }
}
