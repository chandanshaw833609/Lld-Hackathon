package signup;

import UserModule.User;
import UserModule.UserMetaData;

public interface SignupStrategy {
    public User processSignup(UserMetaData userMetaData);
}
