package signup;

import user.User;
import user.UserMetaData;

public interface SignupStrategy {
    public User processSignup(UserMetaData userMetaData);
}
